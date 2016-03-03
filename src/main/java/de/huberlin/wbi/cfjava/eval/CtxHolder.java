package de.huberlin.wbi.cfjava.eval;

import de.huberlin.wbi.cfjava.asyntax.Ctx;

public abstract class CtxHolder {
	
	private final Ctx ctx;
	
	public CtxHolder( final Ctx ctx ) {
		
		if( ctx == null )
			throw new IllegalArgumentException( "Context must not be null." );
		
		this.ctx = ctx;

	}
	
	public Ctx getCtx() {
		return ctx;
	}

}
