package de.huberlin.wbi.cfjava.asyntax;

public class Param {

	private Name name;
	private boolean isLst;
	
	public Param( Name name, boolean isLst ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Name must not be null." );

		this.name = name;
		this.isLst = isLst;
	}

	public Name getName() {
		return name;
	}

	public boolean isLst() {
		return isLst;
	}

}
