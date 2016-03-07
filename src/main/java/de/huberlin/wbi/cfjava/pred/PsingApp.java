package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.LamSurrogate;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PsingApp implements Predicate<App> {

	@Override
	public boolean test( App app ) {
		
		PsingArgPair pred;
		LamSurrogate ls;
		Lam lam;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fa;
		
		pred = new PsingArgPair();
		
		ls = app.getLamSurrogate();
		
		if( !( ls instanceof Lam ) )
			return false;
		
		lam = ( Lam )ls;
		li = lam.getSign().getInLst();
		fa = app.getBindMap();
		
		return pred.test( new ArgPair( li, fa ) );
	}
}
