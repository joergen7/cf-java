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
import de.huberlin.wbi.cfjava.data.Alist;

public class EvalFn extends CtxHolder implements Function<Alist<Expr>, Alist<Expr>> {
	
	private final Profiler profiler;
	
	public EvalFn( final Ctx ctx, final Profiler profiler ) {
		super( ctx );
		
		if( profiler == null )
			throw new IllegalArgumentException( "Profiler must not be null." );
		
		this.profiler = profiler;
	}

	@Override
	public Alist<Expr> apply( Alist<Expr> x ) {
		
		Alist<Expr> x1;
		Long tic, toc;
		
		tic = System.currentTimeMillis();
		x1 = x.flatMap( new StepEvalFn( getCtx(), profiler ) );
		toc = System.currentTimeMillis();
		profiler.reportTime( "step_eval", tic, toc-tic );
		
		if( x.equals( x1 ) )
			return x;
		
		return apply( x1 );
	}

}
