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

import de.huberlin.wbi.cfjava.data.Alist;

public class SignTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievable() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s = new Sign( lo, li );
		assertSame( lo, s.getOutLst() );
		assertSame( li, s.getInLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOutLst() {
		
		Sign s;
		Alist<InParam> li;
		
		li = new Alist<>();
		
		s = new Sign( null, li );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyOutLst() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<>();
		li = new Alist<>();
		
		s = new Sign( lo, li );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullInLst() {
		
		Sign s;
		Alist<Param> lo;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		
		s = new Sign( lo, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldNotEqualNullTest() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s = new Sign( lo, li );
		
		assertNotEquals( s, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldNotEqualStringTest() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s = new Sign( lo, li );
		
		assertNotEquals( s, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldEqualItselfTest() {
		
		Sign s;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s = new Sign( lo, li );
		
		assertEquals( s, s );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldEqualWhenSameOutLstAndInLstTest() {
		
		Sign s1, s2;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li = new Alist<>();
		
		s1 = new Sign( lo, li );
		s2 = new Sign( lo, li );
		
		assertEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldNotEqualWhenDifferingOutLstTest() {
		
		Sign s1, s2;
		Alist<Param> lo1, lo2;
		Alist<InParam> li;
		
		lo1 = new Alist<Param>().add( mock( Param.class ) );
		lo2 = new Alist<Param>().add( mock( Param.class ) ).add( mock( Param.class ) );
		li = new Alist<>();
		
		s1 = new Sign( lo1, li );
		s2 = new Sign( lo2, li );
		
		assertNotEquals( s1, s2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void shouldNotEqualWhenDifferingInLstTest() {
		
		Sign s1, s2;
		Alist<Param> lo;
		Alist<InParam> li1, li2;
		
		lo = new Alist<Param>().add( mock( Param.class ) );
		li1 = new Alist<>();
		li2 = new Alist<InParam>().add( mock( InParam.class ) );
		
		s1 = new Sign( lo, li1 );
		s2 = new Sign( lo, li2 );
		
		assertNotEquals( s1, s2 );
	}
}
