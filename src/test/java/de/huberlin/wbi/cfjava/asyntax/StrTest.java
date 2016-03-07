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
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalStrTest() {
		
		Str s1, s2;
		
		s1 = new Str( "blub" );
		s2 = new Str( "blub" );
		
		assertEquals( s1, s2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertEquals( s, s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferentStrTest() {
		
		Str s1, s2;
		
		s1 = new Str( "blub" );
		s2 = new Str( "bla" );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertNotEquals( s, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertNotEquals( s, "blub" );
	}

}
