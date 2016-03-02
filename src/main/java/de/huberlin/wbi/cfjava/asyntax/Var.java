package de.huberlin.wbi.cfjava.asyntax;

public class Var extends SrcLocated implements LamSurrogate, Expr {
	
	private String target;

	public Var( int line, String target ) {
		
		super( line );
		
		if( target == null )
			throw new IllegalArgumentException( "Target string must not be null." );
		
		if( target.isEmpty() )
			throw new IllegalArgumentException( "Target must not be empty." );
		
		this.target = target;
	}

	public String getTarget() {
		return target;
	}	
}
