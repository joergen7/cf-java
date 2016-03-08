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

package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;

public class FutTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertEquals( "f", f.getLamName() );
		assertEquals( 435, f.getId() );
		assertSame( lo, f.getOutLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamNameTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( null, 435, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLamNameTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "", 435, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", -12, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 0, lo );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullFileMapTest() {
		
		Fut f;
		
		f = new Fut( "f", 435, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertNotEquals( f, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertNotEquals( f, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Fut f;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f = new Fut( "f", 435, lo );
		
		assertEquals( f, f );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "f", 435, lo );
		
		assertEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLamNameTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "g", 435, lo );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentIdTest() {
		
		Fut f1, f2;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		f1 = new Fut( "f", 435, lo );
		f2 = new Fut( "f", 436, lo );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentFileMapTest() {
		
		Fut f1, f2;
		Alist<Param> lo1, lo2;
		
		lo1 = new Alist<Param>().add( new Param( new Name( "out1", false ), false ) );
		lo2 = new Alist<Param>().add( new Param( new Name( "out2", false ), false ) );
		
		f1 = new Fut( "f", 435, lo1 );
		f2 = new Fut( "f", 435, lo2 );
		
		assertNotEquals( f1, f2 );
	}
}
