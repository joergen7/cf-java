package de.huberlin.wbi.cfjava.asyntax;

public class Select extends MultiValue implements Expr {

	private Fut fut;
	
	public Select( int line, int channel, Fut fut ) {
		
		super( line, channel );
		
		if( fut == null )
			throw new IllegalArgumentException( "Future must not be null." );
		
		this.fut = fut;
	}

	public Fut getFut() {
		return fut;
	}
}
