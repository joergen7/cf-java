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
}
