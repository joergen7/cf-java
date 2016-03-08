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

public class ForBody extends ContentHolder implements Body {

	private final Lang lang;
	
	public ForBody( final Lang lang, final String content ) {
		
		super( content );
		
		if( lang == null )
			throw new IllegalArgumentException( "Language must not be null." );
		
		this.lang = lang;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ForBody rhs;
		
		if( !( obj instanceof ForBody ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ForBody )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( lang, rhs.lang ).isEquals();
	}
	
	public Lang getLang() {
		return lang;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 919, 647 )
			.appendSuper( super.hashCode() )
			.append( lang ).toHashCode();
	}
}
