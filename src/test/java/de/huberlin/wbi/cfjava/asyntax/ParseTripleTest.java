package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ParseTripleTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, rho, gamma );
		
		assertSame( query, pt.getQuery() );
		assertSame( rho, pt.getRho() );
		assertSame( gamma, pt.getGamma() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullQueryTest() {
		
		ParseTriple pt;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( null, rho, gamma );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRhoTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, null, gamma );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullGammaTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		
		pt = new ParseTriple( query, rho, null );
	}
}
