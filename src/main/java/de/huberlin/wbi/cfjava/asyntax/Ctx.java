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

package de.huberlin.wbi.cfjava.asyntax;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Ctx {

	private final Amap<String, Alist<Expr>> rho;
	private final Function<App, Fut> mu;
	private final Amap<String, Lam> gamma;
	private final Amap<ResultKey, Alist<Expr>> omega;
	
	public Ctx( final Amap<String, Alist<Expr>> rho, final Function<App, Fut> mu,
			final Amap<String, Lam> gamma, final Amap<ResultKey, Alist<Expr>> omega ) {
		
		if( rho == null )
			throw new IllegalArgumentException( "Rho must not be null." );
		
		if( mu == null )
			throw new IllegalArgumentException( "Mu must not be null." );
		
		if( gamma == null )
			throw new IllegalArgumentException( "Gamma must not be null." );
		
		if( omega == null )
			throw new IllegalArgumentException( "Omega must not be null." );
		
		this.rho = rho;
		this.mu = mu;
		this.gamma = gamma;
		this.omega = omega;
	}

	public Amap<String, Alist<Expr>> getRho() {
		return rho;
	}

	public Function<App, Fut> getMu() {
		return mu;
	}

	public Amap<String, Lam> getGamma() {
		return gamma;
	}

	public Amap<ResultKey, Alist<Expr>> getOmega() {
		return omega;
	}

	@Override
	public String toString() {		
		return new StringBuffer().append( '{' ).append( rho ).append( ',' )
			.append( mu ).append( ',' ).append( gamma ).append( ',' )
			.append( omega ).append( '}' ).toString();
	}
}
