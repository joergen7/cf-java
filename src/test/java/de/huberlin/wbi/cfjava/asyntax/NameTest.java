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
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertNotEquals( n, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertNotEquals( n, "blub" );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertEquals( n, n );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "p", true );
		
		assertEquals( n1, n2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentParamNameTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "q", true );
		
		assertNotEquals( n1, n2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentFileFlagTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "p", false );
		
		assertNotEquals( n1, n2 );
	}
}
