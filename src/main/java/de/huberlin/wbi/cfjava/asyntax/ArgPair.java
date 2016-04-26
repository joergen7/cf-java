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

public class ArgPair {
	
	private final Alist<InParam> inParamLst;
	private final Amap<String, Alist<Expr>> bindMap;

	public ArgPair( final Alist<InParam> inParamLst, final Amap<String, Alist<Expr>> bindMap ) {
		
		if( inParamLst == null )
			throw new IllegalArgumentException( "Input parameter list must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.inParamLst = inParamLst;
		this.bindMap = bindMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ArgPair rhs;
		
		if( !( obj instanceof ArgPair ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ArgPair )obj;
		
		return new EqualsBuilder()
			.append( inParamLst, rhs.inParamLst )
			.append( bindMap, rhs.bindMap ).isEquals();
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}
	
	public Alist<InParam> getInParamLst() {
		return inParamLst;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 3, 5 )
			.append( inParamLst )
			.append( bindMap ).toHashCode();
	}
	
	public String toString() {
		return new StringBuffer()
			.append( '{' )
			.append( inParamLst )
			.append( ',' )
			.append( bindMap )
			.append( '}').toString();
	}

}
