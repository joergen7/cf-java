package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class LamTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, "f", sign, body );
		
		assertEquals( 12, l.getLine() );
		assertEquals( "f", l.getLamName() );
		assertSame( sign, l.getSign() );
		assertSame( body, l.getBody() );
	}
	
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( -12, "f", sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 0, "f", sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamNameTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, null, sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLamNameTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, "", sign, body );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullSignTest() {
		
		Lam l;
		Body body;
		
		body = mock( Body.class );
		
		l = new Lam( 12, "f", null, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBodyTest() {
		
		Lam l;
		Sign sign;
		
		sign = mock( Sign.class );
		
		l = new Lam( 12, "f", sign, null );
	}
}
