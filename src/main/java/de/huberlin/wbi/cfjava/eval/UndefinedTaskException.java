package de.huberlin.wbi.cfjava.eval;

import de.huberlin.wbi.cfjava.asyntax.Var;

public class UndefinedTaskException extends RuntimeException {

	private static final long serialVersionUID = 5697065869051658650L;

	public UndefinedTaskException( Var v ) {
		super( "Line "+v.getLine()+": In task application. A task "+v.getLabel()+" is not defined." );
	}
}
