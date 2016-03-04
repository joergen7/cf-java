package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class SrcLocated {

	private final int line;
	
	public SrcLocated( final int line ) {
		
		if( line <= 0 )
			throw new IllegalArgumentException( "Line number must be positive." );
		
		this.line = line;
	}

	public int getLine() {
		return line;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 883, 71 ).append( line ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		SrcLocated rhs;
		
		if( !( obj instanceof SrcLocated ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( SrcLocated )obj;
		
		return line == rhs.line;
	}

}
