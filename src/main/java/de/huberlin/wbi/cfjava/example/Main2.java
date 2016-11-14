package de.huberlin.wbi.cfjava.example;

import java.io.IOException;

import de.huberlin.wbi.cfjava.cuneiform.RemoteWorkflow;

public class Main2 {

	public static void main( String[] args ) throws IOException, InterruptedException {
		
		RemoteWorkflow workflow;
		
		System.out.println( "Sending workflow up ..." );
		
		workflow = new RemoteWorkflow( "localhost", "my workflow", "5;" );
		
		System.out.println( "Receiving request ..." );
		System.out.println( workflow.getNextRequest() );
		
		Thread.sleep( 100000 );
		
		System.out.println( "Shutting down ..." );
	}
}
