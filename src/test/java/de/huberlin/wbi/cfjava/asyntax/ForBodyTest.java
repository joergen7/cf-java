package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

public class ForBodyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( Lang.BASH, fb.getLang() );
		assertEquals( "bla blub", fb.getContent() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLangTest() {
		
		ForBody fb;
		
		fb = new ForBody( null, "bla blub" );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullContentTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertNotEquals( fb, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertNotEquals( fb, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( fb, fb );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( fb1, fb2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLangTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.PYTHON, "bla blub" );
		
		assertNotEquals( fb1, fb2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentContentTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.BASH, "bla blub shalala" );
		
		assertNotEquals( fb1, fb2 );
	}
}
