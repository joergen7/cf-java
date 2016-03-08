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

public class ParamTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertSame( name, p.getName() );
		assertTrue( p.isLst() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullName() {
		
		Param p;
		
		p = new Param( null, false );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertNotEquals( p, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertNotEquals( p, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Param p;
		Name name;
		
		name = mock( Name.class );
		
		p = new Param( name, true );
		
		assertEquals( p, p );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Param p1, p2;
		Name name;
		
		name = mock( Name.class );
		
		p1 = new Param( name, true );
		p2 = new Param( name, true );
		
		assertEquals( p1, p2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentNameTest() {
		
		Param p1, p2;
		Name name1, name2;
		
		name1 = mock( Name.class );
		name2 = mock( Name.class );
		
		p1 = new Param( name1, true );
		p2 = new Param( name2, true );
		
		assertNotEquals( p1, p2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLstFlagTest() {
		
		Param p1, p2;
		Name name;
		
		name = mock( Name.class );
		
		p1 = new Param( name, true );
		p2 = new Param( name, false );
		
		assertNotEquals( p1, p2 );
	}
}
