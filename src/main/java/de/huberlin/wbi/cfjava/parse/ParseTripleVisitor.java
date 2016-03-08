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

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ParseTripleVisitor extends CuneiformBaseVisitor<ParseTriple> {
	
	@Override
	public ParseTriple visitCompoundexpr( CuneiformParser.CompoundexprContext ctx ) {
		
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		if( ctx.NIL() != null )
			return new ParseTriple( new Alist<Expr>(), rho, gamma );
		
		return this.visitChildren( ctx );
	}


	@Override
	protected ParseTriple aggregateResult( ParseTriple a, ParseTriple b ) {
		
		if( a == null )
			return b;
		
		return a;
	}
}
