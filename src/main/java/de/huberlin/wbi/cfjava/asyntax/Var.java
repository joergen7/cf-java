package de.huberlin.wbi.cfjava.asyntax;

public class Var implements LamSurrogate {
	
	private String target;
	private int line;

	public Var( int line, String target ) {
		
		if( target == null )
			throw new IllegalArgumentException( "Target string must not be null." );
		
		if( target.isEmpty() )
			throw new IllegalArgumentException( "Target must not be empty." );
		
		if( line <= 0 )
			throw new IllegalArgumentException( "Line number must be positive." );
		
		this.target = target;
		this.line = line;
	}

	public String getTarget() {
		return target;
	}

	public int getLine() {
		return line;
	}

	
}
