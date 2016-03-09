package de.huberlin.wbi.cfjava.eval;

import de.huberlin.wbi.cfjava.asyntax.Var;

public class UndefinedVariableException extends RuntimeException {

	private static final long serialVersionUID = 2166351442125034476L;

	public UndefinedVariableException( Var v ) {
		super( "Line "+v.getLine()+": In variable reference. A variable "+v.getLabel()+" is not defined." );
	}
}
