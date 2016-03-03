package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class CtxTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( rho, mu, gamma, omega );
		
		assertSame( rho, c.getRho() );
		assertSame( mu, c.getMu() );
		assertSame( gamma, c.getGamma() );
		assertSame( omega, c.getOmega() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRhoTest() {
		
		Ctx c;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( null, mu, gamma, omega );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullMuTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( rho, null, gamma, omega );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullGammaTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		omega = new Amap<>();
		
		c = new Ctx( rho, mu, null, omega );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOmegaTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		
		c = new Ctx( rho, mu, gamma, null );
	}
}
