package de.huberlin.wbi.cfjava.cuneiform;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.IdHolder;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Reply extends IdHolder {
	
	private final Amap<String, Alist<Expr>> retMap;
	private final Alist<String> out;
	private final long tstart;
	private final long tdur;
	
	public Reply( int id, Amap<String, Alist<Expr>> retMap, Alist<String> out,
		long tstart, long tdur) {
		
		super( id );
		
		if( out == null )
			throw new IllegalArgumentException( "Output string list must not be null." );
		
		if( retMap == null )
			throw new IllegalArgumentException( "Return map must not be null." );
		
		if( tstart < 0 )
			throw new IllegalArgumentException( "Start time must not be negative." );
		
		if( tdur < 0 )
			throw new IllegalArgumentException( "Duration time must not be negative." );
		
		this.retMap = retMap;
		this.out = out;
		this.tstart = tstart;
		this.tdur = tdur;
	}

	public Amap<String, Alist<Expr>> getRetMap() {
		return retMap;
	}

	public Alist<String> getOut() {
		return out;
	}

	public long getTstart() {
		return tstart;
	}

	public long getTdur() {
		return tdur;
	}

}
