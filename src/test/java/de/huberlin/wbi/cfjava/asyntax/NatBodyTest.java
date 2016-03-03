package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class NatBodyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		NatBody nb;
		Amap<String, Alist<Expr>> bodyMap;
		
		bodyMap = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>()
				.add( new Str( "blub" ) ) );
		
		nb = new NatBody( bodyMap );
		
		assertSame( bodyMap, nb.getBodyMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBodyMapTest() {
		
		NatBody nb;
		
		nb = new NatBody( null );		
	}
}
