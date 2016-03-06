package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class MultiValue extends SrcLocated {

	private final int channel;

	public MultiValue( final int line, final int channel ) {
		
		super( line );
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );

		this.channel = channel;
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		MultiValue rhs;
		
		if( !( obj instanceof MultiValue ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( MultiValue )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( channel, rhs.channel ).isEquals();
	}
	
	public int getChannel() {
		return channel;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 293, 601 )
			.appendSuper( super.hashCode() )
			.append( channel ).toHashCode();
	}
}
