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

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Body;
import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.LamSurrogate;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.pred.FinalAmapPred;
import de.huberlin.wbi.cfjava.pred.SingPred;

public class StepFn extends CtxHolder implements Function<Expr, Alist<Expr>> {

	public StepFn( final Ctx ctx ) {
		super( ctx );
	}

	@Override
	public Alist<Expr> apply( Expr x ) {
				
		if( x instanceof Str )
			return new Alist<Expr>().add( x );
		
		if( x instanceof Var )
			return applyVar( ( Var )x );
		
		if( x instanceof Select )
			return applySelect( ( Select )x );
		
		if( x instanceof App )
			return applyApp( ( App )x );
		
		throw new UnsupportedOperationException(
			"Evaluation of "+x.getClass()+" expression not supported." );
	}

	private Alist<Expr> applyVar( Var var ) {
			
		String label;
		Amap<String, Alist<Expr>> rho;

		rho = getCtx().getRho();
		label = var.getLabel();
		
		if( !rho.isKey( label ) )
			throw new UndefinedVariableException( var );
		
		return rho.get( label );
	}
	
	private Alist<Expr> applySelect( Select select ) {
		
		Param param;
		Alist<Param> lo;
		Fut fut;
		int channel, id;
		ResultKey resultKey;
		Amap<ResultKey, Alist<Expr>> omega;
		String label;

		omega = getCtx().getOmega();
		channel = select.getChannel();
		fut = select.getFut();
		id = fut.getId();
		lo = fut.getOutLst();
		param = lo.nth( channel );
		label = param.getName().getLabel();
		
		resultKey = new ResultKey( label, id );
		
		if( !omega.isKey( resultKey ) )
			return new Alist<Expr>().add( select );
		
		return omega.get( resultKey );	
	}
	
	private Alist<Expr> applyApp( App app ) {
			
		Var var;
		String label;
		LamSurrogate lamSurrogate;
		Amap<String, Lam> gamma;
		Amap<String, Alist<Expr>> fa;
		Lam lam;
		int line;
		int channel;
		SingPred singPred;
		FinalAmapPred finalAmapPred;
		Body body;
		Fut fut;

		lamSurrogate = app.getLamSurrogate();
		fa = app.getBindMap();
		line = app.getLine();
		channel = app.getChannel();

		if( lamSurrogate instanceof Var ) {
			
			var = ( Var )lamSurrogate;
			gamma = getCtx().getGamma();
			label = var.getLabel();
		
			if( !gamma.isKey( label ) )
				throw new UndefinedTaskException( var );
			
			lam = gamma.get( label );
			
			return new Alist<Expr>().add( new App( line, channel, lam, fa ) );
		}

		lam = ( Lam )lamSurrogate;
		singPred = new SingPred();
		
		if( !singPred.test( app ) )
			throw new UnsupportedOperationException( "Enumeration not yet supported." );
		
		finalAmapPred = new FinalAmapPred();
		
		if( !finalAmapPred.test( fa ) )
			throw new UnsupportedOperationException( "Stepping binding map not yet supported." );
		
		body = lam.getBody();
		
		if( body instanceof ForBody ) {

			fut = getCtx().getMu().apply( app );
			return new Alist<Expr>().add( new Select( line, channel, fut ) );
		}
		
		throw new UnsupportedOperationException( "Stepping of native body not yet supported." );
		
		
	}
}
