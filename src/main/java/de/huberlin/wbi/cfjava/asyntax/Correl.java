package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;

public class Correl implements InParam {
	
	private final Alist<Name> nameLst;

	public Correl( final Alist<Name> nameLst ) {
		
		if( nameLst == null )
			throw new IllegalArgumentException( "Name list must not be null." );
		
		this.nameLst = nameLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Correl rhs;
		
		if( !( obj instanceof Correl ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Correl )obj;
		
		return nameLst.equals( rhs.nameLst );
	}
	
	public Alist<Name> getNameLst() {
		return nameLst;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 73, 389 ).append( nameLst ).toHashCode();
	}

}
