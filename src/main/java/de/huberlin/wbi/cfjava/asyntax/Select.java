package de.huberlin.wbi.cfjava.asyntax;

public class Select extends MultiValue implements Expr {

	private final Fut fut;
	
	public Select( final int line, final int channel, final Fut fut ) {
		
		super( line, channel );
		
		if( fut == null )
			throw new IllegalArgumentException( "Future must not be null." );
		
		this.fut = fut;
	}

	public Fut getFut() {
		return fut;
	}
}
