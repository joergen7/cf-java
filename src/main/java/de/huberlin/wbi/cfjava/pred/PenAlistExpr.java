package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.data.Alist;

public class PenAlistExpr implements Predicate<Alist<Expr>> {

	@Override
	public boolean test( Alist<Expr> x ) {
		return x.all( new PenExpr() );
	}

}
