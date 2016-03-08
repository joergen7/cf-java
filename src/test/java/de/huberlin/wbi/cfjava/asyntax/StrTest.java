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


public class StrTest {

	@SuppressWarnings( "static-method" )
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Str s;
		
		s = new Str( "blub" );
		assertEquals( "blub", s.getContent() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullArgTest() {
		
		Str s;
		
		s = new Str( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalStrTest() {
		
		Str s1, s2;
		
		s1 = new Str( "blub" );
		s2 = new Str( "blub" );
		
		assertEquals( s1, s2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertEquals( s, s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferentStrTest() {
		
		Str s1, s2;
		
		s1 = new Str( "blub" );
		s2 = new Str( "bla" );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertNotEquals( s, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Str s;
		
		s = new Str( "blub" );
		
		assertNotEquals( s, "blub" );
	}

}
