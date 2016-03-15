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
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.LamSurrogate;
import de.huberlin.wbi.cfjava.asyntax.NatBody;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.pred.FinalAlistExprPred;
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
		
		if( x instanceof Cnd )
			return applyCnd( ( Cnd )x );
		
		throw new UnsupportedOperationException(
			"Evaluation of "+x.getClass()+" expression not supported." );
	}

	private Alist<Expr> applyApp( App app ) {
			
		Var var;
		String label;
		LamSurrogate lamSurrogate;
		Amap<String, Lam> gamma;
		Amap<String, Alist<Expr>> fa, fb;
		Lam lam, lam1;
		int line, channel;
		SingPred singPred;
		FinalAmapPred finalAmapPred;
		FinalAlistExprPred finalAlistExprPred;
		Body body;
		Fut fut;
		Param param;
		Alist<Param> lo;
		Sign sign;
		NatBody natBody, natBody1;
		Alist<Expr> v0, v1;
		Ctx theta, theta1;
		Function<App, Fut> mu;
		Amap<ResultKey, Alist<Expr>> omega;
		String n;
		StepAssocFn stepAssocFn;

		singPred = new SingPred();
		finalAmapPred = new FinalAmapPred();
		finalAlistExprPred = new FinalAlistExprPred();

		lamSurrogate = app.getLamSurrogate();
		fa = app.getBindMap();
		line = app.getLine();
		channel = app.getChannel();
		theta = getCtx();
		gamma = theta.getGamma();
		mu = theta.getMu();
		omega = theta.getOmega();


		if( lamSurrogate instanceof Var ) {
			
			var = ( Var )lamSurrogate;
			label = var.getLabel();
		
			if( !gamma.isKey( label ) )
				throw new UndefinedTaskException( var );
			
			lam = gamma.get( label );
			
			return new Alist<Expr>().add( new App( line, channel, lam, fa ) );
		}

		lam = ( Lam )lamSurrogate;
		sign = lam.getSign();
		lo = sign.getOutLst();

		
		if( !singPred.test( app ) )
			throw new UnsupportedOperationException( "Enumeration not yet supported." );
		
		
		if( !finalAmapPred.test( fa ) ) {
			
			stepAssocFn = new StepAssocFn( theta );
			return new Alist<Expr>()
				.add( new App( line, channel, lam, stepAssocFn.apply( fa ) ) );
		}
		
		body = lam.getBody();
		
		if( body instanceof ForBody ) {

			fut = getCtx().getMu().apply( app );
			return new Alist<Expr>().add( new Select( line, channel, fut ) );
		}
		
		natBody = ( NatBody )body;
		fb = natBody.getBodyMap();
		param = lo.nth( channel );
		n = param.getName().getLabel();
		v0 = fb.get( n );
		
		theta1 = new Ctx( fb.merge( fa ), mu, gamma, omega );
		
		v1 = v0.flatMap( new StepFn( theta1 ) );
		
		if( !finalAlistExprPred.test( v1 ) ) {
			
			natBody1 = new NatBody( fb.put( n, v1 ) );
			lam1 = new Lam( lam.getLine(), lam.getLamName(), sign, natBody1 );
			
			return new Alist<Expr>().add( new App( line, channel, lam1, fa ) );
		}
		
		if( !param.isLst() && v1.size() != 1 )
			throw new OutputSignMismatchException( app );
		
		return v1;
	}

	private Alist<Expr> applyCnd( Cnd x ) {
		
		Alist<Expr> xc1;
		FinalAlistExprPred fpred;
		
		if( x.getCondLst().isEmpty() )
			return x.getElseLst();
		
		fpred = new FinalAlistExprPred();
		
		if( fpred.test( x.getCondLst() ) )
			return x.getThenLst();
		
		xc1 = x.getCondLst().flatMap( this );
		
		return new Alist<Expr>().add( new Cnd( x.getLine(), xc1, x.getThenLst(), x.getElseLst() ) );
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
	
	private Alist<Expr> applyVar( Var var ) {
			
		String label;
		Amap<String, Alist<Expr>> rho;

		rho = getCtx().getRho();
		label = var.getLabel();
		
		if( !rho.isKey( label ) )
			throw new UndefinedVariableException( var );
		
		return rho.get( label );
	}
}
