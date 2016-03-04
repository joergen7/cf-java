package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Lam extends SrcLocated implements LamSurrogate, LamNameHolder {

	private final String lamName;
	private final Sign sign;
	private final Body body;
	
	public Lam( final int line, final String lamName, final Sign sign, final Body body ) {
		
		super( line );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( sign == null )
			throw new IllegalArgumentException( "Signature must not be null." );
		
		if( body == null )
			throw new IllegalArgumentException( "Body must not be null." );
		
		this.lamName = lamName;
		this.sign = sign;
		this.body = body;
	}

	@Override
	public String getLamName() {
		return lamName;
	}

	public Sign getSign() {
		return sign;
	}

	public Body getBody() {
		return body;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 569, 967 )
			.appendSuper( super.hashCode() )
			.append( lamName )
			.append( sign )
			.append( body ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		Lam rhs;
		
		if( !( obj instanceof Lam ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Lam )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( obj ) )
			.append( lamName, rhs.lamName )
			.append( sign, rhs.sign )
			.append( body, rhs.body ).isEquals();		
	}

}
