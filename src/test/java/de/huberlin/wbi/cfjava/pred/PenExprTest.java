package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Lang;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PenExprTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void strShouldBeEnumerableTest() {
		
		PenExpr pred;
		
		pred = new PenExpr();
		
		assertTrue( pred.test( new Str( "blub" ) ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void cndWithSingleStrBranchesShouldBeEnumerableTest() {
		
		PenExpr pred;
		Cnd c;
		
		pred = new PenExpr();
		
		c = new Cnd( 12,
			new Alist<Expr>(),
			new Alist<Expr>().add( new Str( "bla" ) ),
			new Alist<Expr>().add( new Str( "blub" ) ) );
		
		assertTrue( pred.test( c ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void cndWithEmptyThenBrancheShouldNotBeEnumerableTest() {
		
		PenExpr pred;
		Cnd c;
		
		pred = new PenExpr();
		
		c = new Cnd( 12,
			new Alist<Expr>(),
			new Alist<Expr>(),
			new Alist<Expr>().add( new Str( "blub" ) ) );
		
		assertFalse( pred.test( c ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void cndWithEmptyElseBrancheShouldNotBeEnumerableTest() {
		
		PenExpr pred;
		Cnd c;
		
		pred = new PenExpr();
		
		c = new Cnd( 12,
			new Alist<Expr>(),
			new Alist<Expr>().add( new Str( "bla" ) ),
			new Alist<Expr>() );
		
		assertFalse( pred.test( c ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void selectNonLstSingAppShouldBeEnumerableTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		PenExpr pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>() );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		lam = new Lam( 12, "f", sign, forBody );
		bindMap = new Amap<>();
		app = new App( 13, 1, lam, bindMap );
		pred = new PenExpr();
		
		assertTrue( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void selectLstSingAppShouldNotBeEnumerableTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		PenExpr pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), true ) ),
			new Alist<InParam>() );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		lam = new Lam( 12, "f", sign, forBody );
		bindMap = new Amap<>();
		app = new App( 13, 1, lam, bindMap );
		pred = new PenExpr();
		
		assertFalse( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonSingAppShouldNotBeEnumerableTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		PenExpr pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>().add( new Param( new Name( "x", false ), false ) ) );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Var( 1, "blub" ) ) );
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new PenExpr();
		
		assertFalse( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonLstSelectShouldBeEnumerableTest() {
		
		Select s;
		Alist<Param> lo;
		Fut fut;
		PenExpr pred;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		fut = new Fut( "f", 1234, lo );
		
		s = new Select( 12, 1, fut );
		pred = new PenExpr();
		
		assertTrue( pred.test( s ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void lstSelectShouldNotBeEnumerableTest() {
		
		Select s;
		Alist<Param> lo;
		Fut fut;
		PenExpr pred;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), true ) );
		fut = new Fut( "f", 1234, lo );
		
		s = new Select( 12, 1, fut );
		pred = new PenExpr();
		
		assertFalse( pred.test( s ) );
	}
}
