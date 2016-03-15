package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class StepAssocFn extends CtxHolder implements Function<Amap<String, Alist<Expr>>, Amap<String, Alist<Expr>>> {

	public StepAssocFn( Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Amap<String, Alist<Expr>> apply( Amap<String, Alist<Expr>> fa ) {
		
		Amap<String, Alist<Expr>> ret;
		
		ret = new Amap<>();
		
		for( String n : fa.keys() )
			ret = ret.put( n, fa.get( n ).flatMap( new StepFn( getCtx() ) ) );
		
		return ret;
	}

}
