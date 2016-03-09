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

public abstract class CtxHolder {
	
	private final Ctx ctx;
	
	public CtxHolder( final Ctx ctx ) {
		
		if( ctx == null )
			throw new IllegalArgumentException( "Context must not be null." );
		
		this.ctx = ctx;

	}
	
	public Ctx getCtx() {
		return ctx;
	}

}
