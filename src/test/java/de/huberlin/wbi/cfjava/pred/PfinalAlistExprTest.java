package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;

public class PfinalAlistExprTest {

	@SuppressWarnings("static-method")
	@Test
	public void allStrIsFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) );
		pred = new PfinalAlistExpr();
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void EmptyListIsFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<>();
		pred = new PfinalAlistExpr();
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void oneVarIsNotFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Str( "bla" ) ).add( new Var( 12, "x" ) ).add( new Str( "blub" ) );
		pred = new PfinalAlistExpr();
		
		assertFalse( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void allVarIsNotFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Var( 11, "y" ) ).add( new Var( 12, "x" ) );
		pred = new PfinalAlistExpr();
		
		assertFalse( pred.test( x ) );
	}
}
