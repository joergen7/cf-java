package de.huberlin.wbi.cfjava.asyntax;

public class MultiValue extends SrcLocated {

	private int channel;

	public MultiValue( int line, int channel ) {
		
		super( line );
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );

		this.channel = channel;
	}
	
	public int getChannel() {
		return channel;
	}
}
