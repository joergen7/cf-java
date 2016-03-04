package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

public class VarTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Var v;
		
		v = new Var( 12, "bla" );
		assertEquals( 12, v.getLine() );
		assertEquals( "bla", v.getLabel() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		Var v;
		
		v = new Var( 20, null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		Var v;
		
		v = new Var( 23, "" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Var v;
		
		v = new Var( -12, "blub" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Var v;
		
		v = new Var( 0, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertNotEquals( v, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertNotEquals( v, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertEquals( v, v );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalStrTest() {
		
		Var v1, v2;
		
		v1 = new Var( 1, "blub" );
		v2 = new Var( 1, "blub" );
		
		assertEquals( v1, v2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferingLineTest() {
		
		Var v1, v2;
		
		v1 = new Var( 11, "blub" );
		v2 = new Var( 12, "blub" );
		
		assertNotEquals( v1, v2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferingLabelTest() {
		
		Var v1, v2;
		
		v1 = new Var( 12, "blub" );
		v2 = new Var( 12, "bla" );
		
		assertNotEquals( v1, v2 );
	}

}
