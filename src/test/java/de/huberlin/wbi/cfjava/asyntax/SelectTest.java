package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class SelectTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 1, fut );
		
		assertEquals( 12, s.getLine() );
		assertEquals( 1, s.getChannel() );
		assertSame( fut, s.getFut() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( -12, 1, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 0, 1, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegChannelTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, -1, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroChannelTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 0, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullFutTest() {
		
		Select s;
		
		s = new Select( 12, 1, null );
	}
}
