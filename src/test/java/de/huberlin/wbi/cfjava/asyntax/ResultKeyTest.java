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

public class ResultKeyTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );
		
		assertEquals( "out", rk.getLabel() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLabelTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( null, 435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLabelTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "", 435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", -435 );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 0 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertNotEquals( rk, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertNotEquals( rk, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		ResultKey rk;
		
		rk = new ResultKey( "out", 435 );

		assertEquals( rk, rk );
	}

	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out", 435 );

		assertEquals( rk1, rk2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLabelTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out2", 435 );

		assertNotEquals( rk1, rk2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentIdTest() {
		
		ResultKey rk1, rk2;
		
		rk1 = new ResultKey( "out", 435 );
		rk2 = new ResultKey( "out", 436 );

		assertNotEquals( rk1, rk2 );
	}
}
