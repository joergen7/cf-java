package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultKeyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );
		
		assertEquals( "out", rk.getLabel() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( null, 435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "", 435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", -435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 0 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertNotEquals( rk, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertNotEquals( rk, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertEquals( rk, rk );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out", 435 );

		assertEquals( rk1, rk2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLabelTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out2", 435 );

		assertNotEquals( rk1, rk2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentIdTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out", 436 );

		assertNotEquals( rk1, rk2 );
	}
}
