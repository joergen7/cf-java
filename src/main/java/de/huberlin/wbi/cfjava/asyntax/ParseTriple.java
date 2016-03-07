package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ParseTriple {

	private final Alist<Expr> query;
	private final Amap<String, Alist<Expr>> rho;
	private final Amap<String, Lam> gamma;
	
	public ParseTriple( final Alist<Expr> query, final Amap<String, Alist<Expr>> rho, final Amap<String, Lam> gamma ) {
		
		if( query == null )
			throw new IllegalArgumentException( "Query expression list must not be null." );
		
		if( rho == null )
			throw new IllegalArgumentException( "Rho must not be null." );
		
		if( gamma == null )
			throw new IllegalArgumentException( "Gamma must not be null." );
		
		this.query = query;
		this.rho = rho;
		this.gamma = gamma;
	}

	public Alist<Expr> getQuery() {
		return query;
	}

	public Amap<String, Alist<Expr>> getRho() {
		return rho;
	}

	public Amap<String, Lam> getGamma() {
		return gamma;
	}

}
