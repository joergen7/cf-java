package de.huberlin.wbi.cfjava.example;

import java.util.List;
import java.util.Set;

import de.huberlin.wbi.cfjava.cuneiform.Reply;
import de.huberlin.wbi.cfjava.cuneiform.Request;
import de.huberlin.wbi.cfjava.cuneiform.Workflow;

public class Main {

	public static void main( String[] args ) {
		
		String script, summary;
		Workflow workflow;
		boolean isFinished;
		Set<Request> requestSet;
		Reply reply;
		List<String> result;
		
		// Assume, the user provided us the following workflow script:
		script = "deftask greet( out : person )in bash "
			+"*{\n  out=\"Hello $person\"\n\n}*\n\ngreet( person: world ); world = \"Jorgen\";";
		
		System.out.println( "SCRIPT\n"+script );
		
		// Create a workflow object from that script.
		workflow = Workflow.createWorkflow( script );

		System.out.println( "\nORIGINAL\n"+workflow );
		
		// Applying the reduce method attempts to simplify the workflow as far
		// as possible. Reduce returns a boolean telling whether the workflow is
		// finished.
		isFinished = workflow.reduce();
		
		System.out.println( "\nREDUCED (finished: "+isFinished+")\n"+workflow );
		
		// Reducing has produced a new request, which we now have to collect and
		// execute
		requestSet = workflow.getRequestSet();
		
		System.out.println( "\nREQUESTS\n"+requestSet );
		
		// Assume, effi produced the following summary:
		summary = "#{arg => #{\"person\" => [{str,\"Jorgen\"}]},\n"
				+"  id => 1,\n"
				+"  lam => {lam,1,\"greet\",\n"
				+"    {sign,[{param,{name,\"out\",false},false}],\n"
				+"          [{param,{name,\"person\",false},false}]},\n"
				+"    {forbody,bash,\"\\n  out=\\\"Hello $person\\\"\\n\"}},\n"
				+"  out => [],\n"
				+"  ret => #{\"out\" => [{str,\"Hello Jorgen\"}]},\n"
				+"  tdur => 5,\n"
				+"  tstart => 1457946567909}.\n";
		
		System.out.println( "\nSUMMARY\n"+summary );

		// Create a reply object from the summary.
		reply = Reply.createReply( summary );
		
		// Feed the reply object to the workflow
		workflow.addReply( reply );
		
		// Apply the reduce method once again
		isFinished = workflow.reduce();
		
		System.out.println( "\nREDUCED (finished: "+isFinished+")\n"+workflow );
		
		// Finally, extract the result of the workflow
		result = workflow.getResult();
		
		System.out.println( "\nRESULT\n"+result );

	}

}
