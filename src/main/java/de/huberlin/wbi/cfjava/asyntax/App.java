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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class App extends MultiValue implements Expr {
	
	private final Amap<String, Alist<Expr>> bindMap;
	private final LamSurrogate lamSurrogate;
	
	public App( final int line, final int channel, final LamSurrogate lamSurrogate, final Amap<String, Alist<Expr>> bindMap ) {
		
		super( line, channel );

		if( lamSurrogate == null )
			throw new IllegalArgumentException( "Lambda surrogate must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lamSurrogate = lamSurrogate;
		this.bindMap = bindMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		App rhs;
		
		if( !( obj instanceof App ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( App )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( bindMap, rhs.bindMap )
			.append( lamSurrogate, rhs.lamSurrogate ).isEquals();
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}
	
	public LamSurrogate getLamSurrogate() {
		return lamSurrogate;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.appendSuper( super.hashCode() )
			.append( bindMap )
			.append( lamSurrogate ).toHashCode();
	}
}
