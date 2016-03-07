package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;

public class Fut extends IdHolder implements LamNameHolder {
	
	private final Alist<Param> outLst;
	private final String lamName;

	public Fut( final String lamName, final int id, final Alist<Param> outLst ) {
		
		super( id );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		if( outLst == null )
			throw new IllegalArgumentException( "Output parameter list must not be null." );
		
		this.lamName = lamName;
		this.outLst = outLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Fut rhs;
		
		if( !( obj instanceof Fut ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Fut )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( lamName, rhs.lamName )
			.append( outLst, rhs.outLst ).isEquals();
	}


	public Alist<Param> getOutLst() {
		return outLst;
	}
	
	@Override
	public String getLamName() {
		return lamName;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 443, 787 )
			.appendSuper( super.hashCode() )
			.append( lamName )
			.append( outLst ).toHashCode();
	}

}
