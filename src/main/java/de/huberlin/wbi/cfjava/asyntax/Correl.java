package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;

public class Correl implements InParam {
	
	private final Alist<Name> nameLst;

	public Correl( final Alist<Name> nameLst ) {
		
		if( nameLst == null )
			throw new IllegalArgumentException( "Name list must not be null." );
		
		this.nameLst = nameLst;
	}

	public Alist<Name> getNameLst() {
		return nameLst;
	}

}
