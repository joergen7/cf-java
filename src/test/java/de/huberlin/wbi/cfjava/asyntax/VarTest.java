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

public class VarTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Var v;
		
		v = new Var( 12, "bla" );
		assertEquals( 12, v.getLine() );
		assertEquals( "bla", v.getLabel() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		Var v;
		
		v = new Var( 23, "" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Var v;
		
		v = new Var( -12, "blub" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		Var v;
		
		v = new Var( 20, null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Var v;
		
		v = new Var( 0, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalStrTest() {
		
		Var v1, v2;
		
		v1 = new Var( 1, "blub" );
		v2 = new Var( 1, "blub" );
		
		assertEquals( v1, v2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertEquals( v, v );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferingLabelTest() {
		
		Var v1, v2;
		
		v1 = new Var( 12, "blub" );
		v2 = new Var( 12, "bla" );
		
		assertNotEquals( v1, v2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsDifferingLineTest() {
		
		Var v1, v2;
		
		v1 = new Var( 11, "blub" );
		v2 = new Var( 12, "blub" );
		
		assertNotEquals( v1, v2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertNotEquals( v, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Var v;
		
		v = new Var( 12, "blub" );
		
		assertNotEquals( v, "blub" );
	}
}
