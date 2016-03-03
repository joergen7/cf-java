package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertEquals( "p", n.getLabel() );
		assertTrue( n.isFile() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullName() {
		
		Param p;
		
		p = new Param( null, false );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		Name n;
		
		n = new Name( null, false );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		Name n;
		
		n = new Name( "", false );
	}

}
