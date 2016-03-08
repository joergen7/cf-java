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

public class ForBodyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( Lang.BASH, fb.getLang() );
		assertEquals( "bla blub", fb.getContent() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLangTest() {
		
		ForBody fb;
		
		fb = new ForBody( null, "bla blub" );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullContentTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertNotEquals( fb, null );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertNotEquals( fb, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		ForBody fb;
		
		fb = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( fb, fb );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.BASH, "bla blub" );
		
		assertEquals( fb1, fb2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLangTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.PYTHON, "bla blub" );
		
		assertNotEquals( fb1, fb2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentContentTest() {
		
		ForBody fb1, fb2;
		
		fb1 = new ForBody( Lang.BASH, "bla blub" );
		fb2 = new ForBody( Lang.BASH, "bla blub shalala" );
		
		assertNotEquals( fb1, fb2 );
	}
}
