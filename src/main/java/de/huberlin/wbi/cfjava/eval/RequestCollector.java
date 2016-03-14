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

import java.util.Set;
import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.cuneiform.Request;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class RequestCollector implements Function<App, Fut> {
	
	private Amap<Request, Fut> cache;
	private int nextId;
	
	public RequestCollector() {
		cache = new Amap<>();
		nextId = 1;
	}
	
	public Set<Request> getRequestSet() {
		return cache.keys();
	}

	@Override
	public Fut apply( App app ) {
		
		Fut fut;
		Lam lam;
		String lamName;
		Alist<Param> lo;
		Request request;
		Amap<String, Alist<Expr>> fa;
		int id;

		lam = ( Lam )app.getLamSurrogate();
		fa = app.getBindMap();
		id = nextId++;
		
		request = new Request( lam, fa, id );
		
		if( cache.isKey( request ) )
			return cache.get( request );
		
		lamName = lam.getLamName();
		lo = lam.getSign().getOutLst();
		
		fut = new Fut( lamName, id, lo );
		cache = cache.put( request, fut );
		
		return fut;
	}

}
