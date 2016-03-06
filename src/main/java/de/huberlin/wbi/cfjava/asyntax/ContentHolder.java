package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class ContentHolder {

	private final String content;

	public ContentHolder( final String content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Content string must not be null." );
		
		this.content = content;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ContentHolder rhs;
		
		if( !( obj instanceof ContentHolder ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ContentHolder )obj;
		
		return content.equals( rhs.content );
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 131, 23 ).append( content ).toHashCode();
	}

}
