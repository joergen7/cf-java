package de.huberlin.wbi.cfjava.asyntax;

public class MultiValue extends SrcLocated {

	private final int channel;

	public MultiValue( final int line, final int channel ) {
		
		super( line );
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );

		this.channel = channel;
	}
	
	public int getChannel() {
		return channel;
	}
}
