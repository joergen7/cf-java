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

public class PfinalAlistExprTest {

	@SuppressWarnings("static-method")
	@Test
	public void allStrIsFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Str( "bla" ) ).add( new Str( "blub" ) );
		pred = new PfinalAlistExpr();
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void EmptyListIsFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<>();
		pred = new PfinalAlistExpr();
		
		assertTrue( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void oneVarIsNotFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Str( "bla" ) ).add( new Var( 12, "x" ) ).add( new Str( "blub" ) );
		pred = new PfinalAlistExpr();
		
		assertFalse( pred.test( x ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void allVarIsNotFinalTest() {
		
		Alist<Expr> x;
		PfinalAlistExpr pred;
		
		x = new Alist<Expr>().add( new Var( 11, "y" ) ).add( new Var( 12, "x" ) );
		pred = new PfinalAlistExpr();
		
		assertFalse( pred.test( x ) );
	}
}
