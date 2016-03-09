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

import java.util.function.Predicate;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Select;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;

public class EnExprPred implements Predicate<Expr> {

	@Override
	public boolean test( Expr x ) {
		
		Cnd c;
		App a;
		Alist<Expr> xt, xe;
		SingPred psing;
		Lam lam;
		Sign sign;
		Alist<Param> lo;
		Param param;
		int channel;
		Select s;
		
		if( x instanceof Str )
			return true;
		
		if( x instanceof Cnd ) {
		
			c = ( Cnd )x;
			xt = c.getThenLst();
			xe = c.getElseLst();
			
			if( xt.size() != 1 )
				return false;
			
			if( xe.size() != 1 )
				return false;
			
			return test( xt.hd() ) && test( xe.hd() );
		}
		
		if( x instanceof App ) {
			
			a = ( App )x;
			psing = new SingPred();
			
			if( !psing.test( a ) )
				return false;
			
			channel = a.getChannel(); 
			lam = ( Lam )a.getLamSurrogate();
			sign = lam.getSign();
			lo = sign.getOutLst();
			param = lo.nth( channel );
			
			return !param.isLst();
			
		}
		
		if( x instanceof Select ) {
			
			s = ( Select )x;
			
			channel = s.getChannel();
			lo = s.getFut().getOutLst();
			param = lo.nth( channel );
			
			return !param.isLst();
		}
		
		return false;
	}

}
