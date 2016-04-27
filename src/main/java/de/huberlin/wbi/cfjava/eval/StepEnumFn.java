package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.asyntax.Correl;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class StepEnumFn implements Function<ArgPair, Alist<ArgPair>> {

	@Override
	public Alist<ArgPair> apply( final ArgPair argPair ) {

		Alist<InParam> inParamLst;
		Amap<String,Alist<Expr>> f;
		InParam firstInParam;
		Param firstParam;
		String label;
		Alist<ArgPair> res;
		Alist<Expr> exprLst;
		Correl firstCorrel;
		Alist<Name> lc;
		Alist<Amap<String,Alist<Expr>>> z;
		
		inParamLst = argPair.getInParamLst();
		
		if( inParamLst.isEmpty() )												// (54)
			return new Alist<ArgPair>().add( argPair );
		
		firstInParam = inParamLst.hd();
		f = argPair.getBindMap();
		
		if( firstInParam instanceof Param ) {
			
			firstParam = ( Param )firstInParam;
			
			if( firstParam.isLst() )											// (55)
				return augment(
					apply( new ArgPair( inParamLst.tl(), f ) ),
					firstParam );

			label = firstParam.getName().getLabel();
			exprLst = f.get( label );
			
			if( exprLst.size() == 1 )											// (56)
				return augment(
						apply( new ArgPair( inParamLst.tl(), f ) ),
						firstParam );
			
			res = new Alist<>();												// (57)
			for( Expr x : exprLst )
				
				res = res.add( new ArgPair(
					inParamLst,
					f.put( label, new Alist<Expr>().add( x ) ) ) );
			
			return res.reverse();
		}
		
		if( firstInParam instanceof Correl ) {									// (58)
			
			firstCorrel = ( Correl )firstInParam;
			lc = firstCorrel.getNameLst();
			
			z = corr( lc, f );
			
			res = new Alist<>();
			for( Amap<String,Alist<Expr>> g : z )
				res = res.add( new ArgPair( inParamLst.tl(), g ) );
			
			return augment( res.reverse(), firstCorrel );
		}
		
		throw new UnsupportedOperationException(
			"Cannot enumerate parameter of type "+firstInParam.getClass()+"." );
	}

	private static Alist<ArgPair> augment( Alist<ArgPair> argPairLst, Correl correl ) {
		
		Alist<ArgPair> res;
		Alist<InParam> prefix;
		
		prefix = new Alist<>();
		for( Name name : correl.getNameLst() )
			prefix = prefix.add( new Param( name, false ) );
		prefix = prefix.reverse();
		
		res = new Alist<>();
		for( ArgPair argPair : argPairLst )
			res = res.add( new ArgPair(
				prefix.append( argPair.getInParamLst() ),
				argPair.getBindMap() ) );
		
		return res.reverse();
	}

	private static Alist<Amap<String, Alist<Expr>>> corr( Alist<Name> lc, Amap<String, Alist<Expr>> f ) {
		
		String firstLabel;
		Alist<Expr> firstVal;
		Alist<Amap<String,Alist<Expr>>> fPair;
		Amap<String,Alist<Expr>> fStar, fMinus;
		
		
		firstLabel = lc.hd().getLabel();
		firstVal = f.get( firstLabel );
		
		if( firstVal.isEmpty() )
			return new Alist<>();
		
		fPair = corrstep( lc, f, f );
		
		fStar = fPair.hd();
		fMinus = fPair.tl().hd();
		
		return corr( lc, fMinus ).add( fStar );
	}

	private static Alist<Amap<String, Alist<Expr>>> corrstep( Alist<Name> lc, Amap<String, Alist<Expr>> fStar,
		Amap<String, Alist<Expr>> fMinus) {
		
		String firstLabel;
		Alist<Expr> firstVal;
		Expr a;
		Alist<Expr> b;
		Amap<String,Alist<Expr>> fStar1, fMinus1;
		
		if( lc.isEmpty() )
			return new Alist<Amap<String,Alist<Expr>>>().add( fMinus ).add( fStar );
		
		firstLabel = lc.hd().getLabel();
		firstVal = fMinus.get( firstLabel );
		
		a = firstVal.hd();
		b = firstVal.tl();
		
		fStar1 = fStar.put( firstLabel, new Alist<Expr>().add( a ) );
		fMinus1 = fMinus.put( firstLabel, b );
		
		return corrstep( lc.tl(), fStar1, fMinus1 );
	}

	private static Alist<ArgPair> augment( Alist<ArgPair> argPairLst, Param param ) {
		
		Alist<ArgPair> res;
		
		res = new Alist<>();
		for( ArgPair argPair : argPairLst )
			res = res.add( new ArgPair(
				argPair.getInParamLst().add( param ),
				argPair.getBindMap() ) );
		
		return res.reverse();
	}
}