package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Str extends ContentHolder implements Expr {
	
	public Str( final String content ) {
		super( content );
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		if( !( obj instanceof Str ) )
			return false;
		
		if( obj == this )
			return true;
		
		return super.equals( obj );
	}
		
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 863, 389 )
			.appendSuper( super.hashCode() ).toHashCode();
	}
	
	@Override
	public boolean isFinal() {
		return true;
	}
}
