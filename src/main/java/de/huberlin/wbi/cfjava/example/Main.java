package de.huberlin.wbi.cfjava.example;

import java.io.IOException;

import de.huberlin.wbi.cfjava.cuneiform.Workflow;

public class Main {

	public static void main( String[] args ) throws IOException {
		
		String script;
		Workflow workflow;
		
		script = "deftask greet( out : person )in bash *{\n  out=\"Hello $person\"\n}*\n\ngreet( person: \"Jorgen\" );";
		
		System.out.println( "[script]\n" );
		System.out.println( script );
		
		
		workflow = new Workflow( script );

		System.out.println( "\n[workflow]" );
		System.out.println( workflow );

	}

}
