package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;

public class PenAlistExprTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyLstIsEnumerableTest() {
		
		PenAlistExpr pred;
		Alist<Expr> x;
		
		pred = new PenAlistExpr();
		x = new Alist<>();
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void strLstIsEnumerableTest() {
		
		PenAlistExpr pred;
		Alist<Expr> x;
		
		pred = new PenAlistExpr();
		x = new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) );
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void oneVarLstIsNotEnumerableTest() {
		
		PenAlistExpr pred;
		Alist<Expr> x;
		
		pred = new PenAlistExpr();
		x = new Alist<Expr>().add( new Var( 12, "x" ) ).add( new Str( "blub" ) );
		
		assertFalse( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void allVarLstIsNotEnumerableTest() {
		
		PenAlistExpr pred;
		Alist<Expr> x;
		
		pred = new PenAlistExpr();
		x = new Alist<Expr>().add( new Var( 12, "x" ) ).add( new Var( 13, "blub" ) );
		
		assertFalse( pred.test( x ) );
	}


}
