package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;


public class StrTest {

	@SuppressWarnings( "static-method" )
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Str s;
		
		s = new Str( "blub" );
		assertEquals( "blub", s.getContent() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullArgTest() {
		
		Str s;
		
		s = new Str( null );
		
	}
}
