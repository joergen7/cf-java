package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Param implements InParam {

	private final boolean isLst;
	private final Name name;
	
	public Param( final Name name, final boolean isLst ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Name must not be null." );

		this.name = name;
		this.isLst = isLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Param rhs;
		
		if( !( obj instanceof Param ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Param )obj;
		
		return new EqualsBuilder()
			.append( name, rhs.name )
			.append( isLst, rhs.isLst ).isEquals();
	}

	public Name getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 647, 701 )
			.append( name )
			.append( isLst ).toHashCode();
	}

	public boolean isLst() {
		return isLst;
	}
}
