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
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class StepFn extends CtxHolder implements Function<Expr, Alist<Expr>> {

	public StepFn( final Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Alist<Expr> apply( Expr x ) {
		
		Var var;
		Amap<String, Alist<Expr>> rho;
		String label;
		Select select;
		Param param;
		Alist<Param> lo;
		Fut fut;
		int channel, id;
		ResultKey resultKey;
		Amap<ResultKey, Alist<Expr>> omega;
		
		if( x instanceof Str )
			return new Alist<Expr>().add( x );
		
		if( x instanceof Var ) {
			
			var = ( Var )x;
			rho = getCtx().getRho();
			label = var.getLabel();
			
			if( !rho.isKey( label ) )
				throw new UndefinedVariableException( var );
			
			return rho.get( label );
		}
		
		if( x instanceof Select ) {
			
			select = ( Select )x;
			omega = getCtx().getOmega();
			channel = select.getChannel();
			fut = select.getFut();
			id = fut.getId();
			lo = fut.getOutLst();
			param = lo.nth( channel );
			label = param.getName().getLabel();
			
			resultKey = new ResultKey( label, id );
			
			if( !omega.isKey( resultKey ) )
				return new Alist<Expr>().add( x );
			
			return omega.get( resultKey );
			
		}
		
		throw new UnsupportedOperationException(
			"Evaluation of "+x.getClass()+" expression not supported." );
	}

}
