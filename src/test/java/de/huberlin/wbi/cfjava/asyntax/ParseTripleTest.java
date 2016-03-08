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

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

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
}
