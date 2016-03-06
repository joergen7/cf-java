package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class NatBody implements Body {

	private final Amap<String, Alist<Expr>> bodyMap;
	
	public NatBody( final Amap<String, Alist<Expr>> bodyMap ) {
		
		if( bodyMap == null )
			throw new IllegalArgumentException( "Body map must not be null." );
		
		this.bodyMap = bodyMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		NatBody rhs;
		
		if( !( obj instanceof NatBody ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( NatBody )obj;
		
		return bodyMap.equals( rhs.bodyMap );
	}
	
	public Amap<String, Alist<Expr>> getBodyMap() {
		return bodyMap;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 103, 101 ).append( bodyMap ).toHashCode();
	}

}
