package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;

public class EvalExpr extends CtxHolder implements Function<Expr, Alist<Expr>> {

	public EvalExpr( final Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Alist<Expr> apply( Expr x ) {
		
		if( x instanceof Str )
			return new Alist<Expr>().add( x );
		
		throw new UnsupportedOperationException(
			"Evaluation of "+x.getClass()+" expression not supported." );
	}

}
