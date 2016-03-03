package de.huberlin.wbi.cfjava.pred;

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.Str;

public class Pfinal implements Predicate<Object> {

	@Override
	public boolean test( Object t ) {
		throw new UnsupportedOperationException( "General object cannot be tested for finality." );
	}
	
	@SuppressWarnings({ "unused", "static-method" })
	public boolean test( Str s ) {
		return true;
	}

}
