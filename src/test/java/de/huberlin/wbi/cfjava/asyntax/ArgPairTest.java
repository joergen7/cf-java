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

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ArgPairTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ArgPair ap;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> bindMap;
		
		li = new Alist<>();
		bindMap = new Amap<>();
		
		ap = new ArgPair( li, bindMap );
		
		assertSame( li, ap.getInParamLst() );
		assertSame( bindMap, ap.getBindMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullInParamLstTest() {
		
		ArgPair ap;
		Amap<String, Alist<Expr>> bindMap;
		
		bindMap = new Amap<>();
		
		ap = new ArgPair( null, bindMap );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindMapTest() {
		
		ArgPair ap;
		Alist<InParam> li;
		
		li = new Alist<>();
		
		ap = new ArgPair( li, null );
	}
}
