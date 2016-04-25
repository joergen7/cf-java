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

public class CorrelTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgShouldBeRetrievableTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c = new Correl( lc );
		
		assertSame( lc, c.getNameLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnEmptyNameLstTest() {
		
		Correl c;
		
		c = new Correl( new Alist<>() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnDegenNameLstTest() {
		
		Correl c;
		
		c = new Correl( new Alist<Name>().add( mock( Name.class ) ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorThrowsIaeOnNullNameLstTest() {
		
		Correl c;
		
		c = new Correl( null );
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c = new Correl( lc );		
		
		assertNotEquals( c, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Correl c;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c = new Correl( lc );		
		
		assertNotEquals( c, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Correl c1, c2;
		Alist<Name> lc;
		
		lc = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c1 = new Correl( lc );		
		c2 = new Correl( lc );		
		
		assertEquals( c1, c2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentNameLstTest() {
		
		Correl c1, c2;
		Alist<Name> lc1, lc2;
		
		lc1 = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) );
		lc2 = new Alist<Name>().add( mock( Name.class ) ).add( mock( Name.class ) ).add( mock( Name.class ) );
		
		c1 = new Correl( lc1 );
		c2 = new Correl( lc2 );
		
		assertNotEquals( c1, c2 );
	}
}
