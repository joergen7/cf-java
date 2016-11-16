package de.huberlin.wbi.cfjava.cuneiform;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import org.json.JSONArray;

public class RemoteWorkflow {
	
	private static final int CF_PORT           = 17489;
	private static final String CF_PROTOCOL    = "cf_protcl";
	private static final String CF_VSN         = "0.1.0";
	private static final String CF_LANG        = "cuneiform";
	
	private static final String LABEL_MSGTYPE  = "msg_type";
	private static final String LABEL_LANG     = "lang";
	private static final String LABEL_CONTENT  = "content";
	private static final String LABEL_PROTOCOL = "protocol";
	private static final String LABEL_VSN      = "vsn";
	private static final String LABEL_DATA     = "data";
	private static final String LABEL_RESULT   = "result";
	private static final String LABEL_LINE     = "line";
	private static final String LABEL_MODULE   = "module";
	private static final String LABEL_REASON   = "reason";
	private static final String LABEL_SUPPL    = "suppl";
	private static final String LABEL_APPLINE  = "app_line";
	private static final String LABEL_ID       = "id";
	private static final String LABEL_LAMNAME  = "lam_name";
	private static final String LABEL_SCRIPT   = "script";
	private static final String LABEL_OUTPUT   = "output";
	
	private static final String MSGTYPE_HALTOK = "halt_ok";
	private static final String MSGTYPE_WORKFLOW = "workflow";
	private static final String MSGTYPE_HALTEWORKFLOW = "halt_eworkflow";
	private static final String MSGTYPE_SUBMIT = "submit";
	private static final String MSGTYPE_HALTETASK = "halt_etask";
	
	private final DataOutputStream os;
	private final DataInputStream is;
	private final Socket socket;
	private final LinkedList<JSONObject> requestQueue;
	private HaltMsg haltMsg;

	public RemoteWorkflow( String host, JSONObject suppl, String content ) throws IOException {
		
		byte[] wfMsg;

		if( host == null )
			throw new IllegalArgumentException( "Host must not be null." );

		if( suppl == null )
			throw new IllegalArgumentException( "Tag must not be null." );

		if( content == null )
			throw new IllegalArgumentException( "Content must not be null." );
		
		requestQueue = new LinkedList<>();
		haltMsg = null;


		// Open TCP connection
		socket = new Socket( host, CF_PORT );
		os = new DataOutputStream( socket.getOutputStream() );
		is = new DataInputStream( socket.getInputStream() ) ;
		
		// send workflow message
		wfMsg = createWorkflowMsg( suppl, content ).toString().getBytes();
		os.writeInt( wfMsg.length );
		os.write( wfMsg );
		os.flush();
	}
	
	public void addReply( JSONObject reply ) throws IOException {
		
		byte[] msg;
		
		msg = reply.toString().getBytes();
		
		os.writeInt( msg.length );
		os.write( msg );
		os.flush();
	}
	
	public boolean isRunning() {
		return haltMsg == null;
	}
	
	public boolean hasNextRequest() {
		return !requestQueue.isEmpty();
	}
	
	public JSONObject nextRequest() {
		
		if( requestQueue.isEmpty() )
			return null;
		
		return requestQueue.pop();
	}

	private static JSONObject createWorkflowMsg( JSONObject suppl, String content ) {
		
		JSONObject wfMsg, data;
		
		data = new JSONObject();
		data.put( LABEL_LANG, CF_LANG );
		data.put( LABEL_CONTENT, content );
		data.put( LABEL_SUPPL, suppl );

		wfMsg = new JSONObject();
		wfMsg.put( LABEL_PROTOCOL, CF_PROTOCOL );
		wfMsg.put( LABEL_VSN, CF_VSN );
		wfMsg.put( LABEL_MSGTYPE, MSGTYPE_WORKFLOW );
		wfMsg.put( LABEL_DATA, data );
		
		
		return wfMsg;
	}
	
	public HaltMsg getHaltMsg() {
		return haltMsg;
	}
	
	public void update() throws IOException {
		
		JSONObject msg, data;
		JSONArray result;
		int line, appLine;
		String module, reason, id, lamName, script, output;
		
		
		while( ( msg = nextMsg() ) != null ) {
			
			data = msg.getJSONObject( LABEL_DATA );
			
			switch( msg.getString( LABEL_MSGTYPE ) ) {
			
				case MSGTYPE_HALTOK :
					
					result = data.getJSONArray( LABEL_RESULT );
					haltMsg = new HaltMsg( result );
					break;
					
				case MSGTYPE_HALTEWORKFLOW :

					line = data.getInt( LABEL_LINE );
					module = data.getString( LABEL_MODULE );
					reason = data.getString( LABEL_REASON );
					haltMsg = new HaltMsg( line, module, reason );
					break;
					
				case MSGTYPE_SUBMIT :
					
					requestQueue.add( msg );
					break;
					
				case MSGTYPE_HALTETASK :
					
					appLine = data.getInt( LABEL_APPLINE );
					id = data.getString( LABEL_ID );
					lamName = data.getString( LABEL_LAMNAME );
					script = data.getString( LABEL_SCRIPT );
					output = data.getString( LABEL_OUTPUT );
					haltMsg = new HaltMsg( id, appLine, lamName, script, output );
					break;

				default:
					throw new UnsupportedOperationException(
						"Message type not recognized: "+msg.getString( LABEL_MSGTYPE ) );
			}
		}
	}
	
	private JSONObject nextMsg() throws IOException {

		int len;
		byte[] buf;
		String target;
				
		if( is.available() == 0 )
			return null;

		len = is.readInt();
		
		System.out.println( "Length: "+len );
		
		buf = new byte[ len ];
		
		is.readFully( buf );

        target = new String( buf );
        System.out.println( "Received data: "+ target );
        return new JSONObject( String.valueOf( target ) );
	}
}