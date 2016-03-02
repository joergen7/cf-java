package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class App {
	
	private int line;
	private int channel;
	private LamSurrogate lamSurrogate;
	private Amap<String, Alist<Expr>> bindMap;
	
	public App( int line, int channel, LamSurrogate lamSurrogate, Amap<String, Alist<Expr>> bindMap ) {

		if( line <= 0 )
			throw new IllegalArgumentException( "Line number must be positive." );
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );
		
		if( lamSurrogate == null )
			throw new IllegalArgumentException( "Lambda surrogate must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.line = line;
		this.channel = channel;
		this.lamSurrogate = lamSurrogate;
		this.bindMap = bindMap;
	}

	public Object getLine() {
		return line;
	}

	public int getChannel() {
		return channel;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	public LamSurrogate getLamSurrogate() {
		return lamSurrogate;
	}
}
