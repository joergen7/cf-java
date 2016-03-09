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
import de.huberlin.wbi.cfjava.asyntax.ArgPair;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.LamSurrogate;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class SingPred implements Predicate<App> {
	
	private class SingArgPairPred implements Predicate<ArgPair> {

		public SingArgPairPred() {}

		@Override
		public boolean test( ArgPair argPair ) {
			
			Alist<InParam> li;
			InParam h;
			Alist<InParam> t;
			Param param;
			Amap<String, Alist<Expr>> fa;
			Alist<Expr> x;
			
			li = argPair.getInParamLst();
			fa = argPair.getBindMap();
			
			if( li.isEmpty() )
				return true;
			
			h = li.hd();
			t = li.tl();
			
			if( !( h instanceof Param ) )
				return false;
			
			param = ( Param )h;
			
			if( param.isLst() )
				return test( new ArgPair( t, fa ) );
			
			x = fa.get( param.getName().getLabel() );
			
			if( x.size() != 1 )
				return false;
			
			return test( new ArgPair( t, fa ) );
				
		}

	}

	@Override
	public boolean test( App app ) {
		
		SingArgPairPred pred;
		LamSurrogate ls;
		Lam lam;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> fa;
		
		pred = new SingArgPairPred();
		
		ls = app.getLamSurrogate();
		
		if( !( ls instanceof Lam ) )
			return false;
		
		lam = ( Lam )ls;
		li = lam.getSign().getInLst();
		fa = app.getBindMap();
		
		return pred.test( new ArgPair( li, fa ) );
	}
}
