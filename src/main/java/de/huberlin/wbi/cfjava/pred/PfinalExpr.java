package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.Expr;

public class PfinalExpr implements Predicate<Expr> {

	@Override
	public boolean test( Expr x ) {
		return x.isFinal();
	}
	
	

}
