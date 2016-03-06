package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Name implements LabelHolder {

	private final boolean isFile;
	private final String label;
	
	public Name( final String label, final boolean isFile ) {
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
		this.isFile = isFile;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Name rhs;
		
		if( !( obj instanceof Name ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Name )obj;
		
		return new EqualsBuilder()
			.append( label, rhs.label )
			.append( isFile, rhs.isFile ).isEquals();
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 883, 347 )
			.append( label )
			.append( isFile ).toHashCode();
	}
	
	public boolean isFile() {
		return isFile;
	}
}
