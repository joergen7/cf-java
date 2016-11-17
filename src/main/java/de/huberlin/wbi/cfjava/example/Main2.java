package de.huberlin.wbi.cfjava.example;

import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import de.huberlin.wbi.cfjava.cuneiform.RemoteWorkflow;

public class Main2 {

	public static void main( String[] args ) throws IOException, InterruptedException {
		
		RemoteWorkflow workflow;
		JSONObject reply, data, suppl, result_map;
		JSONArray exprLst;
		
		System.out.println( "Sending workflow up." );

		suppl = new JSONObject();
		suppl.put( "bla", "blub" );
		
		workflow = new RemoteWorkflow( "deftask test( out : )in bash *{ out=blub }* test();", "localhost", suppl );

		System.out.println( "Waiting 1 second ..." );
		Thread.sleep( 1000 );
		
		System.out.println( "Updating state." );
		workflow.update();
		
		System.out.println( "is running: "+workflow.isRunning() );
		
		if( workflow.isRunning() ) {
			System.out.println( "Has next request: "+workflow.hasNextRequest() );
		}
		else {
			System.out.println( "Workflow has terminated." );
		}
			
		
		exprLst = new JSONArray( new LinkedList<String>() {{ add( "blub" ); }} );
		
		result_map = new JSONObject();
		result_map.put( "out", exprLst );
		
		data = new JSONObject();
		data.put( "id", "4FF8F8B3EB909BA2C93C3A5BDA8B6D36497B1822DE12AFF2C3574B98D1664E85" );
		data.put( "output", "shalala" );
		data.put( "app_line", 8 );
		data.put( "lam_name", "test" );
		data.put( "script", "out=blub" );
		
		reply = new JSONObject();
		reply.put( "protocol", "cf_protcl" );
		reply.put( "vsn", "0.1.0" );
		reply.put( "msg_type", "reply_error" );
		reply.put( "data", data );
		
		System.out.println( "Sending reply." );
		workflow.addReply( reply );
		
		
		System.out.println( "Waiting 1 second ..." );
		Thread.sleep( 1000 );
		
		System.out.println( "Updating state." );
		workflow.update();
		
		System.out.println( "is running: "+workflow.isRunning() );
		
		if( workflow.isRunning() ) {
			System.out.println( "Has next request: "+workflow.hasNextRequest() );
		}
		else {
			System.out.println( "Workflow has terminated ok: "+workflow.getHaltMsg().isOk() );
		}

		
		System.out.println( "Shutting down ..." );
	}
}
