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

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class CtxTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( rho, mu, gamma, omega );
		
		assertSame( rho, c.getRho() );
		assertSame( mu, c.getMu() );
		assertSame( gamma, c.getGamma() );
		assertSame( omega, c.getOmega() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRhoTest() {
		
		Ctx c;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( null, mu, gamma, omega );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullMuTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		omega = new Amap<>();
		
		c = new Ctx( rho, null, gamma, omega );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullGammaTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<ResultKey, Alist<Expr>> omega;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		omega = new Amap<>();
		
		c = new Ctx( rho, mu, null, omega );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOmegaTest() {
		
		Ctx c;
		Amap<String, Alist<Expr>> rho;
		Function<App, Fut> mu;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		mu = new Function<App, Fut>() { @Override public Fut apply( App a ) { return null; } };
		gamma = new Amap<>();
		
		c = new Ctx( rho, mu, gamma, null );
	}
}
