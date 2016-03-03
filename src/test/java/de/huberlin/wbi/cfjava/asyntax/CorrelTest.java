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
}
