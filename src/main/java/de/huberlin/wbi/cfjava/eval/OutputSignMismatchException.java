package de.huberlin.wbi.cfjava.eval;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Lam;

public class OutputSignMismatchException extends RuntimeException {

	private static final long serialVersionUID = -8043697007700731965L;
	
	public OutputSignMismatchException( App app ) {
		super( "Line "+app.getLine()+": Application of task "
			+( ( Lam )app.getLamSurrogate() ).getLamName()
			+" produces a list where a singular output was declared." );
	}

}
