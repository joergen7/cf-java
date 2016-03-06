package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;

public class CorrelTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) );
		
		c = new Correl( lc );
		
		assertSame( lc, c.getNameLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnNullNameLstTest() {
		
		Correl c;
		
		c = new Correl( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) );
		
		c = new Correl( lc );		
		
		assertNotEquals( c, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) );
		
		c = new Correl( lc );		
		
		assertNotEquals( c, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Correl c1, c2;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) );
		
		c1 = new Correl( lc );		
		c2 = new Correl( lc );		
		
		assertEquals( c1, c2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentNameLstTest() {
		
		Correl c1, c2;
		Alist<Name> lc1, lc2;
		
		lc1 = new Alist<Name>().add( mock( Name.class ) );
		lc2 = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c1 = new Correl( lc1 );
		c2 = new Correl( lc2 );
		
		assertNotEquals( c1, c2 );
	}
}
