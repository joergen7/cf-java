package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;

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
}
