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

package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.parse.ParseTriple;

public class ParseTripleTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, rho, gamma );
		
		assertSame( query, pt.getQuery() );
		assertSame( rho, pt.getRho() );
		assertSame( gamma, pt.getGamma() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullQueryTest() {
		
		ParseTriple pt;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( null, rho, gamma );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRhoTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, null, gamma );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullGammaTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		
		pt = new ParseTriple( query, rho, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, rho, gamma );
		
		assertNotEquals( pt, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, rho, gamma );
		
		assertNotEquals( pt, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		ParseTriple pt;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt = new ParseTriple( query, rho, gamma );
		
		assertEquals( pt, pt );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		ParseTriple pt1, pt2;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt1 = new ParseTriple( query, rho, gamma );
		pt2 = new ParseTriple( query, rho, gamma );
		
		assertEquals( pt1, pt2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentQueryTest() {
		
		ParseTriple pt1, pt2;
		Alist<Expr> query1, query2;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query1 = new Alist<Expr>().add( new Str( "blub" ) );
		query2 = new Alist<Expr>().add( new Str( "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		pt1 = new ParseTriple( query1, rho, gamma );
		pt2 = new ParseTriple( query2, rho, gamma );
		
		assertNotEquals( pt1, pt2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentRhoTest() {
		
		ParseTriple pt1, pt2;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho1, rho2;
		Amap<String, Lam> gamma;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho1 = new Amap<>();
		rho2 = new Amap<String, Alist<Expr>>().put( "x", new Alist<Expr>().add( new Str( "bla" ) ) );
		gamma = new Amap<>();
		
		pt1 = new ParseTriple( query, rho1, gamma );
		pt2 = new ParseTriple( query, rho2, gamma );
		
		assertNotEquals( pt1, pt2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentGammaTest() {
		
		ParseTriple pt1, pt2;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma1, gamma2;
		
		query = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<>();
		gamma1 = new Amap<>();
		gamma2 = new Amap<String, Lam>().put( "f", mock( Lam.class ) );
		
		pt1 = new ParseTriple( query, rho, gamma1 );
		pt2 = new ParseTriple( query, rho, gamma2 );
		
		assertNotEquals( pt1, pt2 );
	}
}
