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
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class EvalAlistExprTest {

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
	
	private final Function<Alist<Expr>, Alist<Expr>> eval0 = new EvalAlistExpr( theta0 );
	
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
}
