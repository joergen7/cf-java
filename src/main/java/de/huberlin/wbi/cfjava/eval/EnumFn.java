package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.data.Alist;

public class EnumFn implements Function<Alist<ArgPair>,Alist<ArgPair>> {

	@Override
	public Alist<ArgPair> apply( Alist<ArgPair> argPairLst ) {
		
		Alist<ArgPair> argPairLst1;
		
		argPairLst1 = argPairLst.flatMap( new StepEnumFn() );
		
		if( argPairLst.equals( argPairLst1 ) )
			return argPairLst;
		
		return apply( argPairLst1 );
	}

}
