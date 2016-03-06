package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;

public class CndTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c = new Cnd( 12, xcond, xthen, xelse );
		
		assertSame( xcond, c.getCondLst() );
		assertSame( xthen, c.getThenLst() );
		assertSame( xelse, c.getElseLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();

		c = new Cnd( -12, xcond, xthen, xelse );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();

		c = new Cnd( 0, xcond, xthen, xelse );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullCondLstTest() {
		
		Cnd c;
		Alist<Expr> xthen, xelse;
		
		xthen = new Alist<>();
		xelse = new Alist<>();

		c = new Cnd( 12, null, xthen, xelse );		
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullThenLstTest() {
		
		Cnd c;
		Alist<Expr> xcond, xelse;
		
		xcond = new Alist<>();
		xelse = new Alist<>();

		c = new Cnd( 12, xcond, null, xelse );		
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullElseLstTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen;
		
		xcond = new Alist<>();
		xthen = new Alist<>();

		c = new Cnd( 12, xcond, xthen, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c = new Cnd( 12, xcond, xthen, xelse );
		
		assertNotEquals( c, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c = new Cnd( 12, xcond, xthen, xelse );
		
		assertNotEquals( c, "blub" );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Cnd c;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c = new Cnd( 12, xcond, xthen, xelse );
		
		assertEquals( c, c );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Cnd c1, c2;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c1 = new Cnd( 12, xcond, xthen, xelse );
		c2 = new Cnd( 12, xcond, xthen, xelse );
		
		assertEquals( c1, c2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingCondExprTest() {
		
		Cnd c1, c2;
		Alist<Expr> xcond1, xcond2, xthen, xelse;
		
		xcond1 = new Alist<>();
		xcond2 = new Alist<Expr>().add( new Str( "blub" ) );
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		c1 = new Cnd( 12, xcond1, xthen, xelse );
		c2 = new Cnd( 12, xcond2, xthen, xelse );
		
		assertNotEquals( c1, c2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingThenExprTest() {
		
		Cnd c1, c2;
		Alist<Expr> xcond, xthen1, xthen2, xelse;
		
		xcond = new Alist<>();
		xthen1 = new Alist<>();
		xthen2 = new Alist<Expr>().add( new Str( "blub" ) );
		xelse = new Alist<>();
		
		c1 = new Cnd( 12, xcond, xthen1, xelse );
		c2 = new Cnd( 12, xcond, xthen2, xelse );
		
		assertNotEquals( c1, c2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingElseExprTest() {
		
		Cnd c1, c2;
		Alist<Expr> xcond, xelse1, xelse2, xthen;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse1 = new Alist<>();
		xelse2 = new Alist<Expr>().add( new Str( "blub" ) );
		
		c1 = new Cnd( 12, xcond, xthen, xelse1 );
		c2 = new Cnd( 12, xcond, xthen, xelse2 );
		
		assertNotEquals( c1, c2 );
	}
}
