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


public class SelectTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 1, fut );
		
		assertEquals( 12, s.getLine() );
		assertEquals( 1, s.getChannel() );
		assertSame( fut, s.getFut() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegChannelTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, -1, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegLineTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( -12, 1, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullFutTest() {
		
		Select s;
		
		s = new Select( 12, 1, null );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroChannelTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 0, fut );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroLineTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 0, 1, fut );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Select s1, s2;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s1 = new Select( 12, 1, fut );
		s2 = new Select( 12, 1, fut );
		
		assertEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 1, fut );
		
		assertEquals( s, s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingChannelTest() {
		
		Select s1, s2;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s1 = new Select( 12, 1, fut );
		s2 = new Select( 12, 2, fut );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingFutTest() {
		
		Select s1, s2;
		Fut fut1, fut2;
		
		fut1 = mock( Fut.class );
		fut2 = mock( Fut.class );
		
		s1 = new Select( 12, 1, fut1 );
		s2 = new Select( 12, 1, fut2 );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferingLineTest() {
		
		Select s1, s2;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s1 = new Select( 12, 1, fut );
		s2 = new Select( 11, 1, fut );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 1, fut );
		
		assertNotEquals( s, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Select s;
		Fut fut;
		
		fut = mock( Fut.class );
		
		s = new Select( 12, 1, fut );
		
		assertNotEquals( s, "blub" );
	}
}
