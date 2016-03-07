package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class PsingArgPair implements Predicate<ArgPair> {

	@Override
	public boolean test( ArgPair argPair ) {
		
		Alist<InParam> li;
		InParam h;
		Alist<InParam> t;
		Param param;
		Amap<String, Alist<Expr>> fa;
		Alist<Expr> x;
		
		li = argPair.getInParamLst();
		fa = argPair.getBindMap();
		
		if( li.isEmpty() )
			return true;
		
		h = li.hd();
		t = li.tl();
		
		if( !( h instanceof Param ) )
			return false;
		
		param = ( Param )h;
		
		if( param.isLst() )
			return test( new ArgPair( t, fa ) );
		
		x = fa.get( param.getName().getLabel() );
		
		if( x.size() != 1 )
			return false;
		
		return test( new ArgPair( t, fa ) );
			
	}

}
