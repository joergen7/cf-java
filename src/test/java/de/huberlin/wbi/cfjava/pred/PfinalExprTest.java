package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PfinalExprTest {

	@SuppressWarnings("static-method")
	@Test
	public void strIsFinalTest() {
		
		Expr e;
		PfinalExpr pred;
		
		e = new Str( "blub" );
		pred = new PfinalExpr();
		
		assertTrue( pred.test( e ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appIsNotFinalTest() {
		
		Expr e;
		Var var;
		Amap<String, Alist<Expr>> fa;
		PfinalExpr pred;
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		e = new App( 12, 1, var, fa );
		pred = new PfinalExpr();
		
		assertFalse( pred.test( e ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void cndIsNotFinalTest() {
		
		Expr e;
		PfinalExpr pred;
		Alist<Expr> xcond, xthen, xelse;
		
		xcond = new Alist<>();
		xthen = new Alist<>();
		xelse = new Alist<>();
		
		e = new Cnd( 12, xcond, xthen, xelse );
		pred = new PfinalExpr();
		
		assertFalse( pred.test( e ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void selectIsNotFinalTest() {
		
		Expr e;
		PfinalExpr pred;
		Fut fut;
		
		fut = mock( Fut.class );
		
		e = new Select( 12, 1, fut );
		pred = new PfinalExpr();
		
		assertFalse( pred.test( e ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void varIsNotFinalTest() {
		
		Expr e;
		PfinalExpr pred;
		
		e = new Var( 12, "blub" );
		pred = new PfinalExpr();
		
		assertFalse( pred.test( e ) );
	}	
}
