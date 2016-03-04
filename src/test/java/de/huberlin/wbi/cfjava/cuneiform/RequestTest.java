package de.huberlin.wbi.cfjava.cuneiform;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class RequestTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();
		
		r = new Request( lam, bindMap );
		
		assertSame( lam, r.getLam() );
		assertSame( bindMap, r.getBindMap() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamTest() {
		
		Request r;
		Amap<String, Alist<Expr>>bindMap;
		
		bindMap = new Amap<>();
		
		r = new Request( null, bindMap );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindMapTest() {
		
		Request r;
		Lam lam;
		
		lam = mock( Lam.class );
		
		r = new Request( lam, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoRequestsWithSameLamAndBindMapAreEqualTest() {
		
		Request r1, r2;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r1 = new Request( lam, bindMap );
		r2 = new Request( lam, bindMap );
		
		assertEquals( r1, r2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoRequestsWithSameLamButDifferentBindMapAreNotEqualTest() {
		
		Request r1, r2;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap1, bindMap2;
		
		lam = mock( Lam.class );
		bindMap1 = new Amap<>();
		bindMap2 = new Amap<String, Alist<Expr>>().put( "bla", new Alist<Expr>().add( new Str( "blub" ) ) );

		r1 = new Request( lam, bindMap1 );
		r2 = new Request( lam, bindMap2 );
		
		assertNotEquals( r1, r2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoRequestsWithSameBindMapButDifferentLamAreNotEqualTest() {
		
		Request r1, r2;
		Lam lam1, lam2;
		Amap<String, Alist<Expr>>bindMap;
		
		lam1 = mock( Lam.class );
		lam2 = mock( Lam.class );
		bindMap = new Amap<>();

		r1 = new Request( lam1, bindMap );
		r2 = new Request( lam2, bindMap );
		
		assertNotEquals( r1, r2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void requestShouldNeverEqualStringTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r = new Request( lam, bindMap );
		
		assertNotEquals( r, "blub" );
	}
}
