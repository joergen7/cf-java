package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.LamSurrogate;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class SetChannelFnTest {

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroChannelTest() {
		
		SetChannelFn scf;
		
		scf = new SetChannelFn( 0 );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegChannelTest() {
		
		SetChannelFn scf;
		
		scf = new SetChannelFn( -1 );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=SetChannelException.class )
	public void applyShouldThrowPeOnSetStrChannelTwoTest() {
		
		SetChannelFn scf;
		
		scf = new SetChannelFn( 2 );
		scf.apply( new Str( "blub" ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void applyShouldDoNothingOnSetStrChannelOneTest() {
		
		Str s1;
		Expr s2;
		SetChannelFn scf;
		
		s1 = new Str( "blub" );
		
		scf = new SetChannelFn( 1 );
		s2 = scf.apply( s1 );
		
		assertEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void applyShouldAlterChannelOnAppTest() {
		
		App a1, a2;
		Expr e;
		SetChannelFn scf;
		
		a1 = new App( 12, 1, mock( LamSurrogate.class ), new Amap<String, Alist<Expr>>() );
		
		scf = new SetChannelFn( 2 );
		e = scf.apply( a1 );
		a2 = ( App )e;
		
		assertEquals( 2, a2.getChannel() );
	}
}
