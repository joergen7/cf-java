package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.data.Alist;

public class EvalAlistExpr extends CtxHolder implements Function<Alist<Expr>, Alist<Expr>> {
	
	public EvalAlistExpr( final Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Alist<Expr> apply( Alist<Expr> x ) {
		return x.flatMap( new EvalExpr( getCtx() ) );
	}

}
