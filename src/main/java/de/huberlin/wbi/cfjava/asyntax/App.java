package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class App extends MultiValue implements Expr {
	
	private LamSurrogate lamSurrogate;
	private Amap<String, Alist<Expr>> bindMap;
	
	public App( int line, int channel, LamSurrogate lamSurrogate, Amap<String, Alist<Expr>> bindMap ) {
		
		super( line, channel );

		if( lamSurrogate == null )
			throw new IllegalArgumentException( "Lambda surrogate must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lamSurrogate = lamSurrogate;
		this.bindMap = bindMap;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	public LamSurrogate getLamSurrogate() {
		return lamSurrogate;
	}
}
