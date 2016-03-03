package de.huberlin.wbi.cfjava.asyntax;

public abstract class IdHolder {

	private final int id;
	
	public IdHolder( final int id ) {
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
