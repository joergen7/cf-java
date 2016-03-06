package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class IdHolder {

	private final int id;
	
	public IdHolder( final int id ) {
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		this.id = id;
	}

	@Override
	public boolean equals( Object obj )  {
		
		IdHolder rhs;
		
		if( !( obj instanceof IdHolder ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( IdHolder )obj;
		
		return id == rhs.id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 131, 997 ).append( id ).toHashCode();
	}
}
