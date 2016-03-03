package de.huberlin.wbi.cfjava.asyntax;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Ctx {

	private final Amap<String, Alist<Expr>> rho;
	private final Function<App, Fut> mu;
	private final Amap<String, Lam> gamma;
	private final Amap<ResultKey, Alist<Expr>> omega;
	
	public Ctx( final Amap<String, Alist<Expr>> rho, final Function<App, Fut> mu,
			final Amap<String, Lam> gamma, final Amap<ResultKey, Alist<Expr>> omega ) {
		
		if( rho == null )
			throw new IllegalArgumentException( "Rho must not be null." );
		
		if( mu == null )
			throw new IllegalArgumentException( "Mu must not be null." );
		
		if( gamma == null )
			throw new IllegalArgumentException( "Gamma must not be null." );
		
		if( omega == null )
			throw new IllegalArgumentException( "Omega must not be null." );
		
		this.rho = rho;
		this.mu = mu;
		this.gamma = gamma;
		this.omega = omega;
	}

	public Amap<String, Alist<Expr>> getRho() {
		return rho;
	}

	public Function<App, Fut> getMu() {
		return mu;
	}

	public Amap<String, Lam> getGamma() {
		return gamma;
	}

	public Amap<ResultKey, Alist<Expr>> getOmega() {
		return omega;
	}

}
