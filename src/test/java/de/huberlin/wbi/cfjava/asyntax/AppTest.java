package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class AppTest {

	@SuppressWarnings({ "static-method" })
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		App a;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		a = new App( 12, 1, var, fa );
		
		assertEquals( 12, a.getLine() );
		assertEquals( 1, a.getChannel() );
		assertSame( var, a.getLamSurrogate() );
		assertSame( fa, a.getBindMap() );
	}
	
	@SuppressWarnings({ "static-method" })
	@Test
	public void constructorLamShouldBeRetrievableTest() {
		
		App a;
		Lam lam;
		Amap<String, Alist<Expr>> fa;
		
		lam = mock( Lam.class );
		fa = new Amap<>();
		
		a = new App( 12, 1, lam, fa );
		
		assertEquals( 12, a.getLine() );
		assertEquals( 1, a.getChannel() );
		assertSame( lam, a.getLamSurrogate() );
		assertSame( fa, a.getBindMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		App a;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		a = new App( -12, 1, var, fa );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		App a;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		a = new App( 0, 1, var, fa );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegChannelTest() {
		
		App a;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		a = new App( 12, -1, var, fa );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroChannelTest() {
		
		App a;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		a = new App( 12, 0, var, fa );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamSurrogateTest() {
		
		App a;
		Amap<String, Alist<Expr>> fa;
		
		fa = new Amap<>();
		
		a = new App( 12, 1, null, fa );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindMapTest() {
		
		App a;
		Var var;
		
		var = new Var( 1, "f" );
		
		a = new App( 12, 1, var, null );
	}
}
