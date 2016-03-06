package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Amap;

public class Fut extends IdHolder implements LamNameHolder {
	
	private final Amap<String, Boolean> fileMap;
	private final String lamName;

	public Fut( final String lamName, final int id, final Amap<String, Boolean> fileMap ) {
		
		super( id );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		if( fileMap == null )
			throw new IllegalArgumentException( "File map must not be null." );
		
		this.lamName = lamName;
		this.fileMap = fileMap;
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
			.append( fileMap, rhs.fileMap ).isEquals();
	}


	public Amap<String, Boolean> getFileMap() {
		return fileMap;
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
			.append( fileMap ).toHashCode();
	}

}
