package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PfinalAmapTest {

	@SuppressWarnings("static-method")
	@Test
	public void emptyMapIsFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		PfinalAmap pred;
		
		m = new Amap<>();
		pred = new PfinalAmap();
		
		assertTrue( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void onlyStrMapIsFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		PfinalAmap pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "shalala" ) ) );
		pred = new PfinalAmap();
		
		assertTrue( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void oneVarMapIsNotFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		PfinalAmap pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "shalala" ) ).add( new Var( 10, "x" ) ) );
		pred = new PfinalAmap();
		
		assertFalse( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void allVarMapIsNotFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		PfinalAmap pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 1, "a" ) ).add( new Var( 2, "b" ) ) )
			.put( "y", new Alist<Expr>().add( new Var( 3, "shalala" ) ).add( new Var( 4, "x" ) ) );
		pred = new PfinalAmap();
		
		assertFalse( pred.test( m ) );
	}
}
