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

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class FinalAmapPredTest {

	@SuppressWarnings("static-method")
	@Test
	public void emptyMapIsFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		FinalAmapPred pred;
		
		m = new Amap<>();
		pred = new FinalAmapPred();
		
		assertTrue( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void onlyStrMapIsFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		FinalAmapPred pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "shalala" ) ) );
		pred = new FinalAmapPred();
		
		assertTrue( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void oneVarMapIsNotFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		FinalAmapPred pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "shalala" ) ).add( new Var( 10, "x" ) ) );
		pred = new FinalAmapPred();
		
		assertFalse( pred.test( m ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void allVarMapIsNotFinalTest() {
		
		Amap<String, Alist<Expr>> m;
		FinalAmapPred pred;
		
		m = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 1, "a" ) ).add( new Var( 2, "b" ) ) )
			.put( "y", new Alist<Expr>().add( new Var( 3, "shalala" ) ).add( new Var( 4, "x" ) ) );
		pred = new FinalAmapPred();
		
		assertFalse( pred.test( m ) );
	}
}
