package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Var extends SrcLocated implements LamSurrogate, Expr, LabelHolder {
	
	private final String label;

	public Var( final int line, final String label ) {
		
		super( line );
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Var rhs;
		
		if( !( obj instanceof Var ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Var )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( label, rhs.label ).isEquals();
	}
	
	@Override
	public String getLabel() {
		return label;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 563, 11 )
			.appendSuper( super.hashCode() )
			.append( label ).toHashCode();
	}
}
