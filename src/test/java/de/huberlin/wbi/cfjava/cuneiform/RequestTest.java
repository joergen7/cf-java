/* Cuneiform: A Functional Language for Large Scale Scientific Data Analysis
 *
 * Copyright 2016 Jörgen Brandt, Marc Bux, and Ulf Leser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
	public void notEqualsNullTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r = new Request( lam, bindMap );
		
		assertNotEquals( r, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r = new Request( lam, bindMap );
		
		assertNotEquals( r, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r = new Request( lam, bindMap );
		
		assertEquals( r, r );
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


}
