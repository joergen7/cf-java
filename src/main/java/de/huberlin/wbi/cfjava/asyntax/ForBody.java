package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ForBody extends ContentHolder implements Body {

	private final Lang lang;
	
	public ForBody( final Lang lang, final String content ) {
		
		super( content );
		
		if( lang == null )
			throw new IllegalArgumentException( "Language must not be null." );
		
		this.lang = lang;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ForBody rhs;
		
		if( !( obj instanceof ForBody ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ForBody )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( lang, rhs.lang ).isEquals();
	}
	
	public Lang getLang() {
		return lang;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 919, 647 )
			.appendSuper( super.hashCode() )
			.append( lang ).toHashCode();
	}
}
