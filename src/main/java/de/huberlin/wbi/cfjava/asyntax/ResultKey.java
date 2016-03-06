package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ResultKey extends IdHolder implements LabelHolder {

	private final String label;
	
	public ResultKey( final String label, final int id ) {
		
		super( id );
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ResultKey rhs;
		
		if( !( obj instanceof ResultKey ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ResultKey )obj;
		
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
		return new HashCodeBuilder( 71, 31 )
			.appendSuper( super.hashCode() )
			.append( label ).toHashCode();
	}

}
