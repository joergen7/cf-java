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

public class NameTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertEquals( "p", n.getLabel() );
		assertTrue( n.isFile() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullName() {
		
		Param p;
		
		p = new Param( null, false );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		Name n;
		
		n = new Name( null, false );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		Name n;
		
		n = new Name( "", false );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertNotEquals( n, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertNotEquals( n, "blub" );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Name n;
		
		n = new Name( "p", true );
		
		assertEquals( n, n );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "p", true );
		
		assertEquals( n1, n2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentParamNameTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "q", true );
		
		assertNotEquals( n1, n2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentFileFlagTest() {
		
		Name n1, n2;
		
		n1 = new Name( "p", true );
		n2 = new Name( "p", false );
		
		assertNotEquals( n1, n2 );
	}
}
