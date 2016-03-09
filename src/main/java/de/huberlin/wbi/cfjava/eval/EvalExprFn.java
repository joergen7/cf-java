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

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;

public class EvalExprFn extends CtxHolder implements Function<Expr, Alist<Expr>> {

	public EvalExprFn( final Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Alist<Expr> apply( Expr x ) {
		
		if( x instanceof Str )
			return new Alist<Expr>().add( x );
		
		throw new UnsupportedOperationException(
			"Evaluation of "+x.getClass()+" expression not supported." );
	}

}
