package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class NatBody implements Body {

	private final Amap<String, Alist<Expr>> bodyMap;
	
	public NatBody( final Amap<String, Alist<Expr>> bodyMap ) {
		
		if( bodyMap == null )
			throw new IllegalArgumentException( "Body map must not be null." );
		
		this.bodyMap = bodyMap;
	}

	public Object getBodyMap() {
		return bodyMap;
	}

}
