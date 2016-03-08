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

import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class NatBody implements Body {

	private final Amap<String, Alist<Expr>> bodyMap;
	
	public NatBody( final Amap<String, Alist<Expr>> bodyMap ) {
		
		if( bodyMap == null )
			throw new IllegalArgumentException( "Body map must not be null." );
		
		this.bodyMap = bodyMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		NatBody rhs;
		
		if( !( obj instanceof NatBody ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( NatBody )obj;
		
		return bodyMap.equals( rhs.bodyMap );
	}
	
	public Amap<String, Alist<Expr>> getBodyMap() {
		return bodyMap;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 103, 101 ).append( bodyMap ).toHashCode();
	}

}
