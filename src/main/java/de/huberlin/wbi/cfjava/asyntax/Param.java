package de.huberlin.wbi.cfjava.asyntax;

public class Param implements InParam {

	private final Name name;
	private final boolean isLst;
	
	public Param( final Name name, final boolean isLst ) {
		
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
