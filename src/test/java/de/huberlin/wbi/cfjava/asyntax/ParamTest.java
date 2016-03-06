package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ParamTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertSame( name, p.getName() );
		assertTrue( p.isLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullName() {
		
		Param p;
		
		p = new Param( null, false );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertNotEquals( p, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertNotEquals( p, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertEquals( p, p );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Param p1, p2;
		Name name;
		
		name = mock( Name.class );
		
		p1 = new Param( name, true );
		p2 = new Param( name, true );
		
		assertEquals( p1, p2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentNameTest() {
		
		Param p1, p2;
		Name name1, name2;
		
		name1 = mock( Name.class );
		name2 = mock( Name.class );
		
		p1 = new Param( name1, true );
		p2 = new Param( name2, true );
		
		assertNotEquals( p1, p2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLstFlagTest() {
		
		Param p1, p2;
		Name name;
		
		name = mock( Name.class );
		
		p1 = new Param( name, true );
		p2 = new Param( name, false );
		
		assertNotEquals( p1, p2 );
	}
}
