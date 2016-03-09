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

package de.huberlin.wbi.cfjava.eval;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.function.Function;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class EvalFnTest {

	private final Function<App, Fut> mu0 = new Function<App, Fut>() {

		@Override
		public Fut apply( App app ) {
			
			Lam lam;
			String lamName;
			int id;
			Random random;
			Sign sign;
			Alist<Param> lo;
			
			random = new Random();
			id = random.nextInt( 1000000000 )+1;
			lam = ( Lam )app.getLamSurrogate();
			lamName = lam.getLamName();
			sign = lam.getSign();
			lo = sign.getOutLst();
			
			return new Fut( lamName, id, lo );
		}
		
		
	};
	
	private final Ctx theta0 = new Ctx(
		new Amap<String, Alist<Expr>>(),
		mu0,
		new Amap<String, Lam>(),
		new Amap<ResultKey, Alist<Expr>>());
	
	private final Function<Alist<Expr>, Alist<Expr>> eval0 = new EvalFn( theta0 );
	
	@Test
	public void nilShouldEvalItselfTest() {
		
		Alist<Expr> x, y;
				
		x = new Alist<>();
		y = eval0.apply( x );
		
		assertEquals( x, y );
	}
	
	@Test
	public void strShouldEvalItselfTest() {
		
		Alist<Expr> x, y;
		
		x = new Alist<Expr>().add( new Str( "bla" ) );
		y = eval0.apply( x );
		
		assertEquals( x, y );
	}
	
	@Test( expected=UndefinedVariableException.class )
	public void undefVarShouldFailTest() {
		
		Alist<Expr> x;
		
		x = new Alist<Expr>().add( new Var( 12, "blub" ) );
		eval0.apply( x );
	}
	
	@Test
	public void defVarShouldEvalToBoundValueTest() {
		
		Alist<Expr> x, y, z;
		Amap<String, Alist<Expr>> rho;
		Ctx theta;
		EvalFn eval;
		
		x = new Alist<Expr>().add( new Var( 12, "x" ) );
		z = new Alist<Expr>().add( new Str( "blub" ) );
		rho = new Amap<String, Alist<Expr>>().put( "x", z );
		theta = new Ctx( rho, mu0, new Amap<String, Lam>(), new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta );
		
		y = eval.apply( x );
		
		assertEquals( z, y );
	}
	
	@Test
	public void defVarShouldCascadeBindingTest() {
		
		Alist<Expr> x, y, z;
		Amap<String, Alist<Expr>> rho;
		Ctx theta;
		EvalFn eval;
		
		x = new Alist<Expr>().add( new Var( 12, "x" ) );
		z = new Alist<Expr>().add( new Str( "blub" ) );
		
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 13, "h" ) ) )
			.put( "h", z );
		
		theta = new Ctx( rho, mu0, new Amap<String, Lam>(), new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta );
		
		y = eval.apply( x );
		
		assertEquals( z, y );
	}

	@Test
	public void defVarShouldCascadeBindingTwiceTest() {
		
		Alist<Expr> x, y, z;
		Amap<String, Alist<Expr>> rho;
		Ctx theta;
		EvalFn eval;
		
		x = new Alist<Expr>().add( new Var( 12, "x" ) );
		z = new Alist<Expr>().add( new Str( "blub" ) );
		
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 13, "h" ) ) )
			.put( "h", new Alist<Expr>().add( new Var( 14, "i" ) ) )
			.put( "i", z );
		
		theta = new Ctx( rho, mu0, new Amap<String, Lam>(), new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta );
		
		y = eval.apply( x );
		
		assertEquals( z, y );
	}
	
	@Test
	public void unfinishedSelectShouldEvalToItselfTest() {
		
		Fut fut;
		Alist<Expr> x, y;
		
		fut = new Fut( "f", 1234,
			new Alist<Param>()
				.add( new Param( new Name( "out", false ), false ) ) );
		
		x = new Alist<Expr>().add( new Select( 12, 1, fut ) );
		
		y = eval0.apply( x );
		
		assertEquals( x, y );
	}
	
	@Test
	public void finishedSelectShouldEvalToResultTest() {
		
		Fut fut;
		Alist<Expr> x, y, expected;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		ResultKey resultKey;
		EvalFn eval;
		
		fut = new Fut( "f", 1234,
			new Alist<Param>()
				.add( new Param( new Name( "out", false ), false ) ) );
		
		x = new Alist<Expr>().add( new Select( 12, 1, fut ) );
		
		resultKey = new ResultKey( "out", 1234 );

		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		rho = new Amap<>();
		gamma = new Amap<>();
		omega = new Amap<ResultKey, Alist<Expr>>().put( resultKey, expected );

		eval = new EvalFn( new Ctx( rho, mu0, gamma, omega ) );

		y = eval.apply( x );
		
		assertEquals( expected, y );
	}
}
