package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;

public class PfinalExpr implements Predicate<Expr> {

	@Override
	public boolean test( Expr x ) {
		
		if( x instanceof Str )
			return true;
		
		return false;
	}
	
	

}
