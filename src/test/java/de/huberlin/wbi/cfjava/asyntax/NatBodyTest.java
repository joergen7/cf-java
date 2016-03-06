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
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		nb = new NatBody( bodyMap );
		
		assertSame( bodyMap, nb.getBodyMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBodyMapTest() {
		
		NatBody nb;
		
		nb = new NatBody( null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		NatBody nb;
		Amap<String, Alist<Expr>> bodyMap;
		
		bodyMap = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		nb = new NatBody( bodyMap );
		
		assertNotEquals( nb, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		NatBody nb;
		Amap<String, Alist<Expr>> bodyMap;
		
		bodyMap = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		nb = new NatBody( bodyMap );
		
		assertNotEquals( nb, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		NatBody nb;
		Amap<String, Alist<Expr>> bodyMap;
		
		bodyMap = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		nb = new NatBody( bodyMap );
		
		assertEquals( nb, nb );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		NatBody nb1, nb2;
		Amap<String, Alist<Expr>> bodyMap;
		
		bodyMap = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		nb1 = new NatBody( bodyMap );
		nb2 = new NatBody( bodyMap );
		
		assertEquals( nb1, nb2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentBodyMapTest() {
		
		NatBody nb1, nb2;
		Amap<String, Alist<Expr>> bodyMap1, bodyMap2;
		
		bodyMap1 = new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>().add( new Str( "blub" ) ) );
		bodyMap2 = new Amap<String, Alist<Expr>>()
				.put( "out", new Alist<Expr>().add( new Str( "blub2" ) ) );
		
		nb1 = new NatBody( bodyMap1 );
		nb2 = new NatBody( bodyMap2 );
		
		assertNotEquals( nb1, nb2 );
	}
}
