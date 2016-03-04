package de.huberlin.wbi.cfjava.cuneiform;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Request {
	
	private final Lam lam;
	private final Amap<String, Alist<Expr>> bindMap;

	public Request( final Lam lam, final Amap<String, Alist<Expr>> bindMap ) {
		
		if( lam == null )
			throw new IllegalArgumentException( "Lambda must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lam = lam;
		this.bindMap = bindMap;
	}

	public Lam getLam() {
		return lam;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	@Override
	public int hashCode() {
		return lam.hashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		Request other;
		
		if( !( obj instanceof Request ) )
			return false;
		
		other = ( Request )obj;
		
		return other.bindMap.equals( bindMap ) && other.lam.equals( lam );
	}
}
