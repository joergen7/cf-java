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

import java.util.Set;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
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
		
		r = new Request( lam, bindMap, 1234 );
		
		assertSame( lam, r.getLam() );
		assertSame( bindMap, r.getBindMap() );
		assertEquals( 1234, r.getId() );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamTest() {
		
		Request r;
		Amap<String, Alist<Expr>>bindMap;
		
		bindMap = new Amap<>();
		
		r = new Request( null, bindMap, 1234 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindMapTest() {
		
		Request r;
		Lam lam;
		
		lam = mock( Lam.class );
		
		r = new Request( lam, null, 1234 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();
		
		r = new Request( lam, bindMap, 0 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();
		
		r = new Request( lam, bindMap, -12 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Request r;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r = new Request( lam, bindMap, 1234 );
		
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

		r = new Request( lam, bindMap, 1234 );
		
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

		r = new Request( lam, bindMap, 1234 );
		
		assertEquals( r, r );
	}
	

	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Request r1, r2;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r1 = new Request( lam, bindMap, 1234 );
		r2 = new Request( lam, bindMap, 1234 );
		
		assertEquals( r1, r2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentBindMapTest() {
		
		Request r1, r2;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap1, bindMap2;
		
		lam = mock( Lam.class );
		bindMap1 = new Amap<>();
		bindMap2 = new Amap<String, Alist<Expr>>().put( "bla", new Alist<Expr>().add( new Str( "blub" ) ) );

		r1 = new Request( lam, bindMap1, 1234 );
		r2 = new Request( lam, bindMap2, 1234 );
		
		assertNotEquals( r1, r2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLamTest() {
		
		Request r1, r2;
		Lam lam1, lam2;
		Amap<String, Alist<Expr>>bindMap;
		
		lam1 = mock( Lam.class );
		lam2 = mock( Lam.class );
		bindMap = new Amap<>();

		r1 = new Request( lam1, bindMap, 1234 );
		r2 = new Request( lam2, bindMap, 1234 );
		
		assertNotEquals( r1, r2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void idNotPartOfEqualityTest() {
		
		Request r1, r2;
		Lam lam;
		Amap<String, Alist<Expr>>bindMap;
		
		lam = mock( Lam.class );
		bindMap = new Amap<>();

		r1 = new Request( lam, bindMap, 1234 );
		r2 = new Request( lam, bindMap, 1235 );
		
		assertEquals( r1, r2 );
	}
	

	@SuppressWarnings("static-method")
	@Test
	public void bindingWithoutStageInFilesTest() {
		
		Request r;
		Lam lam;
		Sign sign;
		ForBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fa;
		
		body = mock( ForBody.class );
		
		lo = new Alist<Param>()
			.add( new Param( new Name( "out", false ), false ) );
		li = new Alist<InParam>()
			.add( new Param( new Name( "x", false ), false ) );
		sign = new Sign( lo, li );
		
		fa = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>()
				.add( new Str( "bla" ) )
				.add( new Str( "blub" ) ) );
		
		lam = new Lam( 12, "f", sign, body );
		
		r = new Request( lam, fa, 1234 );
		assertTrue( r.getStageInFilenameSet().isEmpty() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void bindingWithOneStageArgTest() {
		
		Request r;
		Lam lam;
		Sign sign;
		ForBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fa;
		Set<String> filenameSet;
		
		body = mock( ForBody.class );
		
		lo = new Alist<Param>()
			.add( new Param( new Name( "out", false ), false ) );
		li = new Alist<InParam>()
			.add( new Param( new Name( "x", false ), false ) )
			.add( new Param( new Name( "y", true ), false ) );
		sign = new Sign( lo, li );
		
		fa = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>()
				.add( new Str( "bla" ) )
				.add( new Str( "blub" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "a.txt" ) ).add( new Str( "b.txt" ) ) );
		
		lam = new Lam( 12, "f", sign, body );
		
		r = new Request( lam, fa, 1234 );
		filenameSet = r.getStageInFilenameSet();
		
		assertEquals( 2, filenameSet.size() );
		assertTrue( filenameSet.contains( "a.txt" ) );
		assertTrue( filenameSet.contains( "b.txt" ) );
	}

}
