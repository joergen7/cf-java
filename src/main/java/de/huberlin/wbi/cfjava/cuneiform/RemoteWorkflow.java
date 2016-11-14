package de.huberlin.wbi.cfjava.cuneiform;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class RemoteWorkflow {
	
	private static final int CF_PORT           = 17489;
	private static final String CF_PROTOCOL    = "cf_lang";
	private static final String CF_VSN         = "0.1.0";
	private static final String CF_LANG        = "cuneiform";
	
	private static final String LABEL_MSGTYPE  = "msg_type";
	private static final String LABEL_LANG     = "lang";
	private static final String LABEL_CONTENT  = "content";
	private static final String LABEL_PROTOCOL = "protocol";
	private static final String LABEL_VSN      = "vsn";
	private static final String LABEL_TAG      = "tag";
	private static final String LABEL_DATA     = "data";
	
	private static final String MSGTYPE_HALTOK = "halt_ok";
	private static final String MSGTYPE_WORKFLOW = "workflow";
	private static final String MSGTYPE_HALTERRORWORKFLOW = "halt_error_workflow";
	
	private final String tag;
	private final DataOutputStream os;
	private final DataInputStream is;
	private final Socket socket;
	private final List<JSONObject> requestQueue;
	private Result result;

	public RemoteWorkflow( String host, String tag, String content ) throws IOException {
		
		byte[] wfMsg;

		if( host == null )
			throw new IllegalArgumentException( "Host must not be null." );

		if( tag == null )
			throw new IllegalArgumentException( "Tag must not be null." );

		if( content == null )
			throw new IllegalArgumentException( "Content must not be null." );
		
		requestQueue = new LinkedList<>();
		result = null;

		this.tag = tag;

		// Open TCP connection
		socket = new Socket( host, CF_PORT );
		os = new DataOutputStream( socket.getOutputStream() );
		is = new DataInputStream( socket.getInputStream() ) ;
		
		// send workflow message
		wfMsg = createWorkflowMsg( tag, content ).toString().getBytes();
		os.writeInt( wfMsg.length );
		os.write( wfMsg );
		os.flush();
	}
	
	public boolean hasResult() {
		return result != null;
	}
	
	public boolean hasNextRequest() {
		return !requestQueue.isEmpty();
	}
	
	/*public JSONObject getNextRequest() throws IOException {
		

	}*/

	private static JSONObject createWorkflowMsg( String tag, String content ) {
		
		JSONObject wfMsg, data;
		
		data = new JSONObject();
		data.put( LABEL_LANG, CF_LANG );
		data.put( LABEL_CONTENT, content );

		wfMsg = new JSONObject();
		wfMsg.put( LABEL_PROTOCOL, CF_PROTOCOL );
		wfMsg.put( LABEL_VSN, CF_VSN );
		wfMsg.put( LABEL_TAG, tag );
		wfMsg.put( LABEL_MSGTYPE, MSGTYPE_WORKFLOW );
		wfMsg.put( LABEL_DATA, data );
		
		
		return wfMsg;
	}
	
	public void update() throws IOException {
		
		JSONObject msg;
		
		while( ( msg = nextMsg() ) != null ) {
			
			switch( msg.getString( LABEL_MSGTYPE ) ) {
			
				case MSGTYPE_HALTOK				: result = new Result( ( JSONArray ) msg.get( LABEL_DATA ) ); break;
				case MSGTYPE_HALTERRORWORKFLOW	: // ??; break;
				default							: throw new UnsupportedOperationException(
										"Message type not recognized: "+msg.getString( LABEL_MSGTYPE ) );
			}
		}
	}
	
	private JSONObject nextMsg() throws IOException {

		int len;
		byte[] buf;
		StringBuffer target;
				
		if( is.available() == 0 )
			return null;

		len = is.readInt();
		
		System.out.println( "Length: "+len );
		
		buf = new byte[ len ];
		
		is.readFully( buf );
		
		try( BufferedReader reader = new BufferedReader( new InputStreamReader( new ByteArrayInputStream( buf ) ) ) ) {
		
			target = new StringBuffer();
			while( reader.ready() )
				target.append( ( char )reader.read() );
			System.out.println( "Received data: "+target );
			return new JSONObject( String.valueOf( target ) );
			
		}
	}
}