package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.data.Alist;

public class EnumFn implements Function<Alist<ArgPair>,Alist<ArgPair>> {

	private final Profiler profiler;
	
	public EnumFn( Profiler profiler ) {
		
		if( profiler == null )
			throw new IllegalArgumentException( "Profiler must not be null." );
		
		this.profiler = profiler;
	}
	
	@Override
	public Alist<ArgPair> apply( Alist<ArgPair> argPairLst ) {
		
		Alist<ArgPair> argPairLst1;
		Long tic, toc;
		
		tic = System.currentTimeMillis();
		argPairLst1 = argPairLst.flatMap( new StepEnumFn() );
		toc = System.currentTimeMillis();
		profiler.reportTime( "step_enum", tic, toc-tic );
		
		if( argPairLst.equals( argPairLst1 ) )
			return argPairLst;
		
		return apply( argPairLst1 );
	}

}
