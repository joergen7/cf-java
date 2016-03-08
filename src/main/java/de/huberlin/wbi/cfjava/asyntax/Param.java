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

public class Param implements InParam {

	private final boolean isLst;
	private final Name name;
	
	public Param( final Name name, final boolean isLst ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Name must not be null." );

		this.name = name;
		this.isLst = isLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Param rhs;
		
		if( !( obj instanceof Param ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Param )obj;
		
		return new EqualsBuilder()
			.append( name, rhs.name )
			.append( isLst, rhs.isLst ).isEquals();
	}

	public Name getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 647, 701 )
			.append( name )
			.append( isLst ).toHashCode();
	}

	public boolean isLst() {
		return isLst;
	}
}
