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

package de.huberlin.wbi.cfjava.pred;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Correl;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Lang;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class SingPredTest {

	
	@SuppressWarnings("static-method")
	@Test
	public void appWithoutArgShouldBeSingularTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		SingPred pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>() );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<>();
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new SingPred();
		
		assertTrue( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appBindingSingleStrShouldBeSingularTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		SingPred pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>().add( new Param( new Name( "x", false ), false ) ) );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ) );
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new SingPred();
		
		assertTrue( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appBindingStrLstShouldNotBeSingularTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		SingPred pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>().add( new Param( new Name( "x", false ), false ) ) );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Var( 1, "blub" ) ) );
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new SingPred();
		
		assertFalse( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appWithOnlyAggregateArgsShouldBeSingTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		SingPred pred;
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>().add( new Param( new Name( "x", false ), true ) ) );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Var( 1, "blub" ) ) );
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new SingPred();
		
		assertTrue( pred.test( app ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appWithCorrelArgShouldNotBeSingTest() {
		
		Sign sign;
		ForBody forBody;
		Lam lam;
		App app;
		Amap<String, Alist<Expr>> bindMap;
		SingPred pred;
		
		sign = new Sign(
			new Alist<Param>().add(
				new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>().add(
				new Correl(
					new Alist<Name>()
						.add( new Name( "a", false ) )
						.add( new Name( "b", false ) ) ) ) );
		
		forBody = new ForBody( Lang.BASH, "shalala" );
		
		lam = new Lam( 12, "f", sign, forBody );
		
		bindMap = new Amap<String, Alist<Expr>>()
			.put( "a", new Alist<Expr>().add( new Str( "bla" ) ) )
			.put( "b", new Alist<Expr>().add( new Str( "blub" ) ) );
		
		app = new App( 13, 1, lam, bindMap );
		
		pred = new SingPred();
		
		assertFalse( pred.test( app ) );
	}
}