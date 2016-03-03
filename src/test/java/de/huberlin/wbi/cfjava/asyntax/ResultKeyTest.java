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
}
