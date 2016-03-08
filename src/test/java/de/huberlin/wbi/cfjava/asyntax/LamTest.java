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
import static org.mockito.Mockito.*;

import org.junit.Test;



public class LamTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, "f", sign, body );
		
		assertEquals( 12, l.getLine() );
		assertEquals( "f", l.getLamName() );
		assertSame( sign, l.getSign() );
		assertSame( body, l.getBody() );
	}
	
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( -12, "f", sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 0, "f", sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamNameTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, null, sign, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLamNameTest() {
		
		Lam l;
		Sign sign;
		Body body;
		
		sign = mock( Sign.class );
		body = mock( Body.class );
		
		l = new Lam( 12, "", sign, body );
	}

	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullSignTest() {
		
		Lam l;
		Body body;
		
		body = mock( Body.class );
		
		l = new Lam( 12, "f", null, body );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBodyTest() {
		
		Lam l;
		Sign sign;
		
		sign = mock( Sign.class );
		
		l = new Lam( 12, "f", sign, null );
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Lam l;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l = new Lam( 12, "f", sign, body );
		
		assertNotEquals( l, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Lam l;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l = new Lam( 12, "f", sign, body );
		
		assertNotEquals( l, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Lam l;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l = new Lam( 12, "f", sign, body );
		
		assertEquals( l, l );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoLamsWithSameSignAndBodyAreEqualTest() {
		
		Lam l1, l2;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l1 = new Lam( 12, "f", sign, body );
		l2 = new Lam( 12, "f", sign, body );
		
		assertEquals( l1, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoLamsWithDifferingLineAreNotEqualTest() {
		
		Lam l1, l2;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l1 = new Lam( 12, "f", sign, body );
		l2 = new Lam( 11, "f", sign, body );
		
		assertNotEquals( l1, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoLamsWithDifferingLamNameAreNotEqualTest() {
		
		Lam l1, l2;
		Body body;
		Sign sign;
		
		body = mock( Body.class );
		sign = mock( Sign.class );
		
		l1 = new Lam( 12, "f", sign, body );
		l2 = new Lam( 12, "g", sign, body );
		
		assertNotEquals( l1, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoLamsWithDifferingSignAreNotEqualTest() {
		
		Lam l1, l2;
		Body body;
		Sign sign1, sign2;
		
		body = mock( Body.class );
		sign1 = mock( Sign.class );
		sign2 = mock( Sign.class );
		
		l1 = new Lam( 12, "f", sign1, body );
		l2 = new Lam( 12, "f", sign2, body );
		
		assertNotEquals( l1, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void twoLamsWithDifferingBodyAreNotEqualTest() {
		
		Lam l1, l2;
		Body body1, body2;
		Sign sign;
		
		body1 = mock( Body.class );
		body2 = mock( Body.class );
		sign = mock( Sign.class );
		
		l1 = new Lam( 12, "f", sign, body1 );
		l2 = new Lam( 12, "f", sign, body2 );
		
		assertNotEquals( l1, l2 );
	}
}
