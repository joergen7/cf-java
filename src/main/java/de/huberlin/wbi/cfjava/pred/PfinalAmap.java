package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PfinalAmap implements Predicate<Amap<String,Alist<Expr>>> {

	@Override
	public boolean test( Amap<String, Alist<Expr>> m ) {
		return m.values().all( new PfinalAlistExpr() );
	}

}
