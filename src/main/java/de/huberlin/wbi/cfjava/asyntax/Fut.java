package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Amap;

public class Fut implements LamNameHolder {
	
	private final String lamName;
	private final int id;
	private final Amap<String, Boolean> fileMap;

	public Fut( final String lamName, final int id, final Amap<String, Boolean> fileMap ) {
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		if( fileMap == null )
			throw new IllegalArgumentException( "File map must not be null." );
		
		this.lamName = lamName;
		this.id = id;
		this.fileMap = fileMap;
	}

	@Override
	public String getLamName() {
		return lamName;
	}

	public int getId() {
		return id;
	}

	public Amap<String, Boolean> getFileMap() {
		return fileMap;
	}

}
