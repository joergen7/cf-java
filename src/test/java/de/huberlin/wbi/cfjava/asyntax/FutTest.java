package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;

public class FutTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertEquals( "f", f.getLamName() );
		assertEquals( 435, f.getId() );
		assertSame( lo, f.getOutLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamNameTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( null, 435, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLamNameTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "", 435, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", -12, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 0, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullFileMapTest() {
		
		Fut f;
		
		f = new Fut( "f", 435, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertNotEquals( f, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertNotEquals( f, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertEquals( f, f );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "f", 435, lo );
		
		assertEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLamNameTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "g", 435, lo );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentIdTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "f", 436, lo );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentFileMapTest() {
		
		Fut f1, f2;
		Alist<Param> lo1, lo2;
		
		lo1 = new Alist<Param>().add( new Param( new Name( "out1", false ), false ) );
		lo2 = new Alist<Param>().add( new Param( new Name( "out2", false ), false ) );
		
		f1 = new Fut( "f", 435, lo1 );
		f2 = new Fut( "f", 435, lo2 );
		
		assertNotEquals( f1, f2 );
	}
}
