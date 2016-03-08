package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;

public class PenExpr implements Predicate<Expr> {

	@Override
	public boolean test( Expr x ) {
		
		Cnd c;
		App a;
		Alist<Expr> xt, xe;
		PsingApp psing;
		Lam lam;
		Sign sign;
		Alist<Param> lo;
		Param param;
		int channel;
		Select s;
		
		if( x instanceof Str )
			return true;
		
		if( x instanceof Cnd ) {
		
			c = ( Cnd )x;
			xt = c.getThenLst();
			xe = c.getElseLst();
			
			if( xt.size() != 1 )
				return false;
			
			if( xe.size() != 1 )
				return false;
			
			return test( xt.hd() ) && test( xe.hd() );
		}
		
		if( x instanceof App ) {
			
			a = ( App )x;
			psing = new PsingApp();
			
			if( !psing.test( a ) )
				return false;
			
			channel = a.getChannel(); 
			lam = ( Lam )a.getLamSurrogate();
			sign = lam.getSign();
			lo = sign.getOutLst();
			param = lo.nth( channel );
			
			return !param.isLst();
			
		}
		
		if( x instanceof Select ) {
			
			s = ( Select )x;
			
			channel = s.getChannel();
			lo = s.getFut().getOutLst();
			param = lo.nth( channel );
			
			return !param.isLst();
		}
		
		return false;
	}

}
