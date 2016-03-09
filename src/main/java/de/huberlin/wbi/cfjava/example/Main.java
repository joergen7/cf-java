package de.huberlin.wbi.cfjava.example;

import java.io.IOException;

import de.huberlin.wbi.cfjava.cuneiform.Workflow;

public class Main {

	public static void main( String[] args ) throws IOException {
		
		String script;
		Workflow workflow;
		boolean isFinished;
		
		// Assume the user provided us the following workflow script:
		script = "deftask greet( out : person )in bash "
			+"*{\n  out=\"Hello $person\"\n}*\n\ngreet( person: \"Jorgen\" );";
		
		System.out.println( "[script]\n"+script );
		
		// Create a workflow object from that script.
		workflow = new Workflow( script );

		System.out.println( "\n[original]\n"+workflow );
		
		// Applying the reduce method attempts to simplify the workflow as far
		// as possible. Reduce returns a boolean telling whether the workflow is
		// finished.
		isFinished = workflow.reduce();
		
		System.out.println( "\n[reduced (finished: "+isFinished+")]"+workflow );

	}

}
