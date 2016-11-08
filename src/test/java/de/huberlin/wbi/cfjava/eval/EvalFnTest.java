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
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Correl;
import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Lang;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.NatBody;
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
			
			
			if( !( app.getLamSurrogate() instanceof Lam ) )
				throw new IllegalArgumentException( "Application lambda surrogate is not a lambda expression." );
			
			lam = ( Lam )app.getLamSurrogate();
			
			for( InParam inParam : lam.getSign().getInLst() )
				if( inParam instanceof Correl )
					throw new IllegalArgumentException( "Signature must not contain correlated input parameters." );
			
			random = new Random();
			id = random.nextInt( 1000000000 )+1;
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
	
	private final Function<Alist<Expr>, Alist<Expr>> eval0 = new EvalFn( theta0, new DefaultProfiler() );
	
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
		eval = new EvalFn( theta, new DefaultProfiler() );
		
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
		eval = new EvalFn( theta, new DefaultProfiler() );
		
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
		eval = new EvalFn( theta, new DefaultProfiler() );
		
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

		eval = new EvalFn( new Ctx( rho, mu0, gamma, omega ), new DefaultProfiler() );

		y = eval.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test
	public void noargFnShouldEvalPlainTest() {
		
		Alist<Expr> x, y, expected;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>().put( "out", expected ) );
		
		lam = new Lam( 1, "f", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		y = eval0.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test
	public void noargFnShouldEvalBodyTest() {
		
		Alist<Expr> x, y, expected;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody(
			new Amap<String, Alist<Expr>>()
				.put( "out", new Alist<Expr>().add( new Var( 12, "x" ) ) )
				.put( "x", expected ) );
		
		lam = new Lam( 1, "f", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		y = eval0.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test
	public void fnCallShouldInsertLamTest() {
		
		Alist<Expr> x, y, expected;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Lam> gamma;
		Ctx theta;
		EvalFn eval;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>().put( "out", expected ) );
		
		lam = new Lam( 1, "f", sign, body );
		gamma = new Amap<String, Lam>().put( "f", lam );
		theta = new Ctx( new Amap<String, Alist<Expr>>(), mu0, gamma, new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta, new DefaultProfiler() );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, new Var( 1, "f" ),
					new Amap<String, Alist<Expr>>() ) );
		
		y = eval.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test( expected=RuntimeException.class )
	public void appWithUnboundFnShouldFailTest() {
		
		Alist<Expr> x;

		x = new Alist<Expr>()
				.add( new App( 1, 1, new Var( 1, "f" ),
					new Amap<String, Alist<Expr>>() ) );
		
		eval0.apply( x );
	}
	
	@Test
	public void identityFnShouldEvalArgTest() {
		
		Alist<Expr> x, y, expected;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<InParam>().add( new Param( new Name( "inp", false ), false ) );
		
		sign = new Sign( lo, li );
		body = new NatBody(
			new Amap<String, Alist<Expr>>()
				.put( "out", new Alist<Expr>().add( new Var( 12, "inp" ) ) ) );		
		lam = new Lam( 1, "f", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>().put( "inp", expected ) ) );
		
		y = eval0.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test
	public void multipleOutputsShouldBeBindableTest() {
		
		Alist<Expr> x1, x2, y1, y2, expected1, expected2;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		expected1 = new Alist<Expr>().add( new Str( "bla" ) );
		expected2 = new Alist<Expr>().add( new Str( "blub" ) );
		
		
		lo = new Alist<Param>()
			.add( new Param( new Name( "out2", false ), false ) )
			.add( new Param( new Name( "out1", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody(
			new Amap<String, Alist<Expr>>()
				.put( "out1", expected1 )
				.put( "out2", expected2 ) );
		lam = new Lam( 1, "f", sign, body );
		
		x1 = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		x2 = new Alist<Expr>()
				.add( new App( 1, 2, lam,
					new Amap<String, Alist<Expr>>() ) );

		y1 = eval0.apply( x1 );
		y2 = eval0.apply( x2 );
		
		assertEquals( expected1, y1 );
		assertEquals( expected2, y2 );
	}
	
	@Test( expected=UndefinedVariableException.class )
	public void appShouldIgnoreCallingContextTest() {
		
		Alist<Expr> x;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Lam> gamma;
		Amap<String, Alist<Expr>> rho;
		Ctx theta;
		EvalFn eval;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Var( 2, "x" ) ) ) );
		
		lam = new Lam( 1, "f", sign, body );
		gamma = new Amap<>();
		rho = new Amap<String, Alist<Expr>>().put( "x", new Alist<Expr>().add( new Str( "blub" ) ) );
		theta = new Ctx( rho, mu0, gamma, new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta, new DefaultProfiler() );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		eval.apply( x );
	}
	
	@Test
	public void appShouldHandDownGammaTest() {
		
		Alist<Expr> x, y;

		Alist<Expr> expected;
		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Lam> gamma;
		Amap<String, Alist<Expr>> rho;
		Ctx theta;
		EvalFn eval;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>()
				.add( new App( 1, 1, new Var( 2, "f" ),
					new Amap<String, Alist<Expr>>() ) ) ) );
		
		lam = new Lam( 1, "g", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 3, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		gamma = new Amap<String, Lam>()
			.put( "f", new Lam( 4, "f", sign,
				new NatBody( new Amap<String, Alist<Expr>>()
					.put( "out", expected ) ) ) );
		
		rho = new Amap<>();
		theta = new Ctx( rho, mu0, gamma, new Amap<ResultKey, Alist<Expr>>() );
		eval = new EvalFn( theta, new DefaultProfiler() );
		
		y = eval.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test
	public void bindingShouldOverrideBodyTest() {
		
		Alist<Expr> x, y, expected;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		expected = new Alist<Expr>().add( new Str( "blub" ) );
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<InParam>().add( new Param( new Name( "x", false ), false ) );
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ) )
			.put( "out", new Alist<Expr>().add( new Var( 2, "x" ) ) ) );
		
		lam = new Lam( 1, "f", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>().put( "x", expected ) ) );
		
		y = eval0.apply( x );
		
		assertEquals( expected, y );
	}
	
	@Test( expected=OutputSignMismatchException.class )
	public void returningEmptyListOnNonListOutputChannelShouldFailTest() {
		
		Alist<Expr> x;

		Lam lam;
		Sign sign;
		NatBody body;
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		li = new Alist<>();
		
		sign = new Sign( lo, li );
		body = new NatBody( new Amap<String, Alist<Expr>>()
			.put( "out", new Alist<Expr>() ) );
		
		lam = new Lam( 1, "f", sign, body );
		
		x = new Alist<Expr>()
				.add( new App( 1, 1, lam,
					new Amap<String, Alist<Expr>>() ) );
		
		eval0.apply( x );
	}
	
	@Test
	public void crossProductShouldBeDerivableTest() {
		
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Alist<Expr> e1, e2, f1, f2;
		NatBody natBody;
		Amap<String,Alist<Expr>> fb, fa;
		Lam lam;
		Alist<Expr> app1, app2;
		
		lo = new Alist<Param>()
			.add( new Param( new Name( "out2", false ), false ) )
			.add( new Param( new Name( "out1", false ), false ) );
		
		li = new Alist<InParam>()
			.add( new Param( new Name( "p2", false ), false ) )
			.add( new Param( new Name( "p1", false ), false ) );
		
		sign = new Sign( lo, li );
		
		fb = new Amap<String,Alist<Expr>>()
			.put( "out1", new Alist<Expr>().add( new Var( 10, "p1" ) ) )
			.put( "out2", new Alist<Expr>().add( new Var( 20, "p2" ) ) );
		
		natBody = new NatBody( fb );
		
		lam = new Lam( 30, "f", sign, natBody );

		e1 = new Alist<Expr>()
			.add( new Str( "B" ) )
			.add( new Str( "A" ) );
			
		e2 = new Alist<Expr>()
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		fa = new Amap<String,Alist<Expr>>()
			.put( "p1", e1 )
			.put( "p2", e2 );
		
		app1 = new Alist<Expr>().add( new App( 40, 1, lam, fa ) );
		app2 = new Alist<Expr>().add( new App( 40, 2, lam, fa ) );
		
		f1 = new Alist<Expr>()
			.add( new Str( "B" ) )
			.add( new Str( "B" ) )
			.add( new Str( "A" ) )
			.add( new Str( "A" ) );
				
		f2 = new Alist<Expr>()
			.add( new Str( "2" ) )
			.add( new Str( "1" ) )
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		assertEquals( f1, eval0.apply( app1 ) );
		assertEquals( f2, eval0.apply( app2 ) );
	}
	
	@Test
	public void dotProductShouldBeDerivable1Test() {
	
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Alist<Expr> e1, e2, f1, f2;
		NatBody natBody;
		Amap<String,Alist<Expr>> fb, fa;
		Lam lam;
		Alist<Expr> app1, app2;
	
		lo = new Alist<Param>()
			.add( new Param( new Name( "out2", false ), false ) )
			.add( new Param( new Name( "out1", false ), false ) );
		
		li = new Alist<InParam>()
			.add( new Correl( new Alist<Name>()
				.add( new Name( "p2", false ) )
				.add( new Name( "p1", false ) ) ) );

		sign = new Sign( lo, li );
		
		fb = new Amap<String,Alist<Expr>>()
			.put( "out1", new Alist<Expr>().add( new Var( 10, "p1" ) ) )
			.put( "out2", new Alist<Expr>().add( new Var( 20, "p2" ) ) );
		
		natBody = new NatBody( fb );
		
		lam = new Lam( 30, "f", sign, natBody );

		e1 = new Alist<Expr>()
			.add( new Str( "A" ) );
			
		e2 = new Alist<Expr>()
			.add( new Str( "1" ) );
		
		fa = new Amap<String,Alist<Expr>>()
			.put( "p1", e1 )
			.put( "p2", e2 );
		
		app1 = new Alist<Expr>().add( new App( 40, 1, lam, fa ) );
		app2 = new Alist<Expr>().add( new App( 40, 2, lam, fa ) );
		
		f1 = new Alist<Expr>()
			.add( new Str( "A" ) );
				
		f2 = new Alist<Expr>()
			.add( new Str( "1" ) );
		
		assertEquals( f1, eval0.apply( app1 ) );
		assertEquals( f2, eval0.apply( app2 ) );
	}
	
	@Test
	public void dotProductShouldBeDerivable2Test() {
	
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Alist<Expr> e1, e2, f1, f2;
		NatBody natBody;
		Amap<String,Alist<Expr>> fb, fa;
		Lam lam;
		Alist<Expr> app1, app2;
	
		lo = new Alist<Param>()
			.add( new Param( new Name( "out2", false ), false ) )
			.add( new Param( new Name( "out1", false ), false ) );
		
		li = new Alist<InParam>()
			.add( new Correl( new Alist<Name>()
				.add( new Name( "p2", false ) )
				.add( new Name( "p1", false ) ) ) );

		sign = new Sign( lo, li );
		
		fb = new Amap<String,Alist<Expr>>()
			.put( "out1", new Alist<Expr>().add( new Var( 10, "p1" ) ) )
			.put( "out2", new Alist<Expr>().add( new Var( 20, "p2" ) ) );
		
		natBody = new NatBody( fb );
		
		lam = new Lam( 30, "f", sign, natBody );

		e1 = new Alist<Expr>()
			.add( new Str( "B" ) )
			.add( new Str( "A" ) );
			
		e2 = new Alist<Expr>()
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		fa = new Amap<String,Alist<Expr>>()
			.put( "p1", e1 )
			.put( "p2", e2 );
		
		app1 = new Alist<Expr>().add( new App( 40, 1, lam, fa ) );
		app2 = new Alist<Expr>().add( new App( 40, 2, lam, fa ) );
		
		f1 = new Alist<Expr>()
			.add( new Str( "B" ) )
			.add( new Str( "A" ) );
				
		f2 = new Alist<Expr>()
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		assertEquals( f1, eval0.apply( app1 ) );
		assertEquals( f2, eval0.apply( app2 ) );
	}
	
	@Test
	public void dotProductShouldBeDerivable3Test() {
	
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Alist<Expr> e1, e2, f1, f2;
		NatBody natBody;
		Amap<String,Alist<Expr>> fb, fa;
		Lam lam;
		Alist<Expr> app1, app2;
	
		lo = new Alist<Param>()
			.add( new Param( new Name( "out2", false ), false ) )
			.add( new Param( new Name( "out1", false ), false ) );
		
		li = new Alist<InParam>()
			.add( new Correl( new Alist<Name>()
				.add( new Name( "p2", false ) )
				.add( new Name( "p1", false ) ) ) );

		sign = new Sign( lo, li );
		
		fb = new Amap<String,Alist<Expr>>()
			.put( "out1", new Alist<Expr>().add( new Var( 10, "p1" ) ) )
			.put( "out2", new Alist<Expr>().add( new Var( 20, "p2" ) ) );
		
		natBody = new NatBody( fb );
		
		lam = new Lam( 30, "f", sign, natBody );

		e1 = new Alist<Expr>()
			.add( new Str( "C" ) )
			.add( new Str( "B" ) )
			.add( new Str( "A" ) );
			
		e2 = new Alist<Expr>()
			.add( new Str( "3" ) )
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		fa = new Amap<String,Alist<Expr>>()
			.put( "p1", e1 )
			.put( "p2", e2 );
		
		app1 = new Alist<Expr>().add( new App( 40, 1, lam, fa ) );
		app2 = new Alist<Expr>().add( new App( 40, 2, lam, fa ) );
		
		f1 = new Alist<Expr>()
			.add( new Str( "C" ) )
			.add( new Str( "B" ) )
			.add( new Str( "A" ) );
				
		f2 = new Alist<Expr>()
			.add( new Str( "3" ) )
			.add( new Str( "2" ) )
			.add( new Str( "1" ) );
		
		assertEquals( f1, eval0.apply( app1 ) );
		assertEquals( f2, eval0.apply( app2 ) );
	}
	
	@Test
	public void aggregateShouldConsumeWholeListTest() {
		
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Alist<Expr> e1, e2;
		NatBody body;
		Lam lam;
		Amap<String, Alist<Expr>> fa;
		Alist<Expr> x, y;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), true ) );
		li = new Alist<InParam>().add( new Param( new Name( "inp", false ), true ) );
		sign = new Sign( lo, li );
		
		e1 = new Alist<Expr>().add( new Str( "A" ) );
		e2 = new Alist<Expr>().add( new Str( "B" ) ).add( new Str( "C" ) );

		body = new NatBody( new Amap<String, Alist<Expr>>()
			.put( "out", e1.append( new Alist<Expr>().add( new Var( 1, "inp" ) ) ) ) );
		
		lam = new Lam( 2, "f", sign, body );
		fa = new Amap<String, Alist<Expr>>().put( "inp", e2 );
		
		x = new Alist<Expr>().add( new App( 3, 1, lam, fa ) );
		
		y = eval0.apply( x );
		
		assertEquals( e1.append( e2 ), y );
	}
	
	@Test
	public void cndFalseShouldEvalElseExprTest() {
		
		Alist<Expr> x, xc, xt, xe, y;
		
		xc = new Alist<>();
		xt = new Alist<Expr>().add( new Str( "bla" ) );
		xe = new Alist<Expr>().add( new Str( "blub" ) );
		
		x = new Alist<Expr>().add( new Cnd( 1, xc, xt, xe ) );
		
		y = eval0.apply( x );
		
		assertEquals( xe, y );
	}
	
	@Test
	public void cndEvaluatesConditionBeforeDecision1Test() {
		
		Alist<Expr> x, xc, xt, xe, y;
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fb;
		Lam lam;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), true ) );
		li = new Alist<>();
		sign = new Sign( lo, li );
		
		fb = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>() );
		lam = new Lam( 1, "f", sign, new NatBody( fb ) );
		
		xc = new Alist<Expr>().add( new App( 2, 1, lam, new Amap<String, Alist<Expr>>() ) );
		xt = new Alist<Expr>().add( new Str( "A" ) );
		xe = new Alist<Expr>().add( new Str( "B" ) );
		
		x = new Alist<Expr>().add( new Cnd( 1, xc, xt, xe ) );
		
		y = eval0.apply( x );
		
		assertEquals( xe, y );
	}
	
	@Test
	public void cndEvaluatesConditionBeforeDecision2Test() {
		
		Alist<Expr> x, xc, xt, xe, y;
		Sign sign;
		Alist<Param> lo;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fb;
		Lam lam;
		
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), true ) );
		li = new Alist<>();
		sign = new Sign( lo, li );
		
		fb = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "X" ) ) );
		lam = new Lam( 1, "f", sign, new NatBody( fb ) );
		
		xc = new Alist<Expr>().add( new App( 2, 1, lam, new Amap<String, Alist<Expr>>() ) );
		xt = new Alist<Expr>().add( new Str( "A" ) );
		xe = new Alist<Expr>().add( new Str( "B" ) );
		
		x = new Alist<Expr>().add( new Cnd( 1, xc, xt, xe ) );
		
		y = eval0.apply( x );
		
		assertEquals( xt, y );
	}
	
	@Test
	public void marcTest() {
		
		Alist<Expr> x, e1, y;
		Amap<String,Alist<Expr>> rho, fa, bodyMap;
		Amap<String,Lam> gamma;
		Lam bowtie2Align, perFastq;
		Sign sign;
		Ctx theta;
		Function<Alist<Expr>, Alist<Expr>> evalFn;
		
		
		x = new Alist<Expr>().add( new Var( 23, "sam" ) );
		
		fa = new Amap<String,Alist<Expr>>()
			.put( "fastq2", new Alist<Expr>().add( new Var( 21, "fastq2" ) ) )
			.put( "fastq1", new Alist<Expr>().add( new Var( 20, "fastq1" ) ) );
		
		e1 = new Alist<Expr>().add( new App( 19, 1, new Var( 19, "per-fastq" ), fa ) );
		
		rho = new Amap<String,Alist<Expr>>()
			.put( "fastq2", new Alist<Expr>().add( new Str( "SRR359188_2.filt.fastq" ) ) )
			.put( "sam", e1 )
			.put( "fastq1", new Alist<Expr>().add( new Str( "SRR359188_1.filt.fastq" ) ) );
		
		sign = new Sign( 
			new Alist<Param>().add( new Param( new Name( "sam", true ), false ) ),
			new Alist<InParam>()
				.add( new Correl(
					new Alist<Name>()
						.add( new Name( "fastq2", true ) )
						.add( new Name( "fastq1", true ) ) ) ) );

		
		bowtie2Align = new Lam( 1, "bowtie2-align", sign, new ForBody( Lang.BASH, "\n  sam=bowtie2.sam\n  tar xf $idx\n  bowtie2 -D 5 -R 1 -N 0 -L 22 -i S,0,2.50 \\\n  -p 1 \\\n  --no-unal -x bt2idx -1 $fastq1 -2 $fastq2 -S $sam\n  rm bt2idx.*\n" ) );
		
		bodyMap = new Amap<String,Alist<Expr>>()
			.put( "sam", new Alist<Expr>()
				.add( new App( 11, 1, new Var( 11, "bowtie2-align" ), fa ) ) );
		
		perFastq = new Lam( 10, "per-fastq", sign,
			new NatBody( bodyMap ) );
		
		gamma = new Amap<String,Lam>()
			.put( "bowtie2-align", bowtie2Align )
			.put( "per-fastq", perFastq );
		
		theta = new Ctx( rho, mu0, gamma, new Amap<ResultKey,Alist<Expr>>() );
		
		evalFn = new EvalFn( theta, new DefaultProfiler() );
				
		y = evalFn.apply( x );

		assertTrue( y.hd() instanceof App );
	}
}
