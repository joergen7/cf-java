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
}
