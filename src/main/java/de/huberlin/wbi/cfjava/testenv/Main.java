package de.huberlin.wbi.cfjava.testenv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.json.JSONObject;

import de.huberlin.wbi.cfjava.cuneiform.HaltMsg;
import de.huberlin.wbi.cfjava.cuneiform.RemoteWorkflow;

public class Main {
	
	public static void main( String[] args ) throws IOException, InterruptedException {
		
		RemoteWorkflow wf;
		JSONObject request, reply;
		String id, content, line;
		Path buildDir, location, requestFile, summaryFile, stdoutFile, stderrFile;
		Charset utf8;
		StandardOpenOption create;
		ProcessBuilder processBuilder;
		int trial, exitValue;
		boolean suc;
		IOException ex;
		Process process;
		StringBuffer buf;
		HaltMsg haltMsg;
		
		
		buildDir = Paths.get( "/tmp" );
		utf8 = Charset.forName( "UTF-8" );
		create = StandardOpenOption.CREATE;
		
		// define workflow
		// content = "deftask greet( out : person ) in bash *{ exit -1 }* greet( person: \"Marc\" );";
		String fname = args[ 0 ];
		buf = new StringBuffer();
		try( BufferedReader r = Files.newBufferedReader( Paths.get( fname ) ) ) {
			while( ( line = r.readLine() ) != null )
				buf.append( line ).append( '\n' );
		}
		content = buf.toString();
		
		// create new workflow instance
		wf = new RemoteWorkflow( content );
		
		// main loop
		while( true ) {
			
			Thread.sleep( 1000 );
			System.out.println( "new round ..." );
			
			// update workflow information
			System.out.println( "  fetching data" );
			wf.update();
			
			// checking if we're done
			if( !wf.isRunning() )
				break;
			
			
			// iterate over ready tasks
			while( wf.hasNextRequest() ) {
				
				
				request = wf.nextRequest();
				
				id = request.getJSONObject( "data" ).getString( "id" );
				System.out.println( "  starting request "+id );
				
				location = buildDir.resolve( id );
				requestFile = location.resolve( "request.json" );
				summaryFile = location.resolve( "summary.json" );
				
				// initialize location
				deleteIfExists( location );
				Files.createDirectories( location );
				try( BufferedWriter writer = Files.newBufferedWriter( requestFile, utf8, create ) ) {
					writer.write( request.toString() );
				}
				
				// run script
				processBuilder = new ProcessBuilder( new String[] { "effi", requestFile.toString(), summaryFile.toString() } );
				processBuilder.directory( location.toFile() );
				
				stdoutFile = location.resolve( "stdout.txt" );
				stderrFile = location.resolve( "stderr.txt" );

				processBuilder.redirectOutput( stdoutFile.toFile() );
				processBuilder.redirectError( stderrFile.toFile() );
				
				
				trial = 1;
				suc = false;
				ex = null;
				process = null;
				
				System.out.println( "  starting process." );
				
				do {
					try {
						process = processBuilder.start();
						System.out.println( process );

						suc = true;
					}
					catch( IOException e ) {						
						ex = e;
						Thread.sleep( 100 );
					}
				} while( suc == false && trial++ <= 4 );
				
				if( process == null )
					if( ex != null )
						throw ex;
					else
						throw new RuntimeException(
							"Could not instantiate process but exception is null nevertheless." );
				
				System.out.println( "  waiting for process." );
				
				exitValue = process.waitFor();
				
				System.out.println( "  process returned "+exitValue );
				if( exitValue != 0 ) {
					
					try( BufferedReader reader = Files.newBufferedReader( stdoutFile, utf8 ) ) {						
						while( ( line = reader.readLine() ) != null )
							System.out.println( line );
					}
					try( BufferedReader reader = Files.newBufferedReader( stderrFile, utf8 ) ) {						
						while( ( line = reader.readLine() ) != null )
							System.out.println( line );
					}
					
					throw new RuntimeException( "Effi has shut down uncuccessfully." );
				}
				
				try( BufferedReader reader = Files.newBufferedReader( summaryFile, utf8 ) ) {
					
					buf = new StringBuffer();
					while( ( line = reader.readLine() ) != null )
						buf.append( line ).append( '\n' );
					
					reply = new JSONObject( buf.toString() );
					wf.addReply( reply );
				}
			}
		}
		
		
		haltMsg = wf.getHaltMsg();
		System.out.println( "halted." );
		
		if( haltMsg.isOk() )
			System.out.println( "ok" );
		else
			if( haltMsg.isErrorWorkflow() )
				System.out.println( "eworkflow "+haltMsg.getLine()+" "+haltMsg.getModule()+" "+haltMsg.getReason() );
			else
				System.out.println( "etask" );
	}

	private static void deleteIfExists( Path f ) throws IOException {
		
		if( !Files.exists( f, LinkOption.NOFOLLOW_LINKS ) )
			return;
		
		if( Files.isDirectory( f ) )
			try( DirectoryStream<Path> stream = Files.newDirectoryStream( f ) ) {
				for( Path p : stream )
					deleteIfExists( p );
			}
		
		Files.delete( f );
}
}
