package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Select extends MultiValue implements Expr {

	private final Fut fut;
	
	public Select( final int line, final int channel, final Fut fut ) {
		
		super( line, channel );
		
		if( fut == null )
			throw new IllegalArgumentException( "Future must not be null." );
		
		this.fut = fut;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Select rhs;
		
		if( !( obj instanceof Select ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Select )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( fut, rhs.fut ).isEquals();
	}
	
	public Fut getFut() {
		return fut;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 379, 233 )
			.appendSuper( super.hashCode() )
			.append( fut ).toHashCode();
	}
}
