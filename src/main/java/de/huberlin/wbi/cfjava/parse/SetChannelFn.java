package de.huberlin.wbi.cfjava.parse;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Expr;

public class SetChannelFn implements Function<Expr, Expr> {

	private final int channel;
	
	public SetChannelFn( final int channel ) {
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel number must be positive." );
		
		this.channel = channel;
	}
	
	@Override
	public Expr apply( Expr e ) {
		
		App app;
		
		if( e instanceof App ) {
			
			app = ( App )e;
			
			return new App( app.getLine(), channel, app.getLamSurrogate(),
				app.getBindMap() );
		}
		
		if( channel != 1 )
			throw new SetChannelException();
		
		return e;
	}

}
