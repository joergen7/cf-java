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
}
