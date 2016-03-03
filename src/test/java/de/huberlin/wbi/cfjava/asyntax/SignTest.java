package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;

public class SignTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievable() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s = new Sign( lo, li );
		assertSame( lo, s.getOutLst() );
		assertSame( li, s.getInLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOutLst() {
		
		Sign s;
		Alist<InParam> li;
		
		li = new Alist<>();
		
		s = new Sign( null, li );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyOutLst() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<>();
		li = new Alist<>();
		
		s = new Sign( lo, li );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullInLst() {
		
		Sign s;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		
		s = new Sign( lo, null );
	}
}
