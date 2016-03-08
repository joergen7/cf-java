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

public class Select extends MultiValue implements Expr {

	private final Fut fut;
	
	public Select( final int line, final int channel, final Fut fut ) {
		
		super( line, channel );
		
		if( fut == null )
			throw new IllegalArgumentException( "Future must not be null." );
		
		this.fut = fut;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Select rhs;
		
		if( !( obj instanceof Select ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Select )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( fut, rhs.fut ).isEquals();
	}
	
	public Fut getFut() {
		return fut;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 379, 233 )
			.appendSuper( super.hashCode() )
			.append( fut ).toHashCode();
	}
}
