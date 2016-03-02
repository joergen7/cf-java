package de.huberlin.wbi.cfjava.asyntax;

public abstract class SrcLocated {

	private int line;
	
	public SrcLocated( int line ) {
		
		if( line <= 0 )
			throw new IllegalArgumentException( "Line number must be positive." );
		
		this.line = line;
	}

	public int getLine() {
		return line;
	}

}
