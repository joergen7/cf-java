package de.huberlin.wbi.cfjava.op;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.pred.Pfinal;

public class PfinalTest {

	@SuppressWarnings("static-method")
	@Test( expected=UnsupportedOperationException.class )
	public void catchAllShouldThrowUoeTest() {
		
		Pfinal pf;
		
		pf = new Pfinal();
		
		pf.test( "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void strShouldBeFinalTest() {
		
		Pfinal pf;
		
		pf = new Pfinal();
		
		assertTrue( pf.test( new Str( "blub" ) ) );
	}
}
