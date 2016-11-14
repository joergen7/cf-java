package de.huberlin.wbi.cfjava.cuneiform;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

public class RemoteWorkflow {
	
	private static final int CF_PORT = 17489;
	private static final String CF_PROTOCOL = "cf_lang";
	private static final String CF_VSN = "0.1.0";
	private static final String CF_LANG = "cuneiform";
	
	private final String tag;
	private final DataOutputStream os;
	private final DataInputStream is;
	private final Socket socket;

	public RemoteWorkflow( String host, String tag, String content ) throws IOException {
		
		byte[] wfMsg;

		if( host == null )
			throw new IllegalArgumentException( "Host must not be null." );

		if( tag == null )
			throw new IllegalArgumentException( "Tag must not be null." );

		if( content == null )
			throw new IllegalArgumentException( "Content must not be null." );

		this.tag = tag;
		wfMsg = createWorkflowMsg( tag, content ).toString().getBytes();

		// Open TCP connection
		socket = new Socket( host, CF_PORT );
		os = new DataOutputStream( socket.getOutputStream() );
		is = new DataInputStream( socket.getInputStream() );
			
		os.writeInt( wfMsg.length );
		os.write( wfMsg );
		os.flush();
	}
	
	public JSONObject getNextRequest() throws IOException {
		
		int len;
		byte[] buf;
		JSONObject requestMsg;
		StringBuffer target;
		
		
		len = is.readInt();
		
		System.out.println( "Length: "+len );
		
		buf = new byte[ len ];
		
		is.readFully( buf );
		
		try( BufferedReader reader = new BufferedReader( new InputStreamReader( new ByteArrayInputStream( buf ) ) ) ) {
		
			target = new StringBuffer();
			while( reader.ready() )
				target.append( ( char )reader.read() );
			System.out.println( target );
			requestMsg = new JSONObject( String.valueOf( target ) );
			return requestMsg;
		}
	}

	private static JSONObject createWorkflowMsg( String tag, String content ) {
		
		JSONObject wfMsg, data;
		
		data = new JSONObject();
		data.put( "lang", CF_LANG );
		data.put( "content", content );

		wfMsg = new JSONObject();
		wfMsg.put( "protocol", CF_PROTOCOL );
		wfMsg.put( "vsn", CF_VSN );
		wfMsg.put( "tag", tag );
		wfMsg.put( "msg_type", "workflow" );
		wfMsg.put( "data", data );
		
		
		return wfMsg;
		
	}
}