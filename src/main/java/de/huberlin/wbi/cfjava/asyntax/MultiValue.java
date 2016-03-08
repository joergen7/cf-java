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

public abstract class MultiValue extends SrcLocated {

	private final int channel;

	public MultiValue( final int line, final int channel ) {
		
		super( line );
		
		if( channel <= 0 )
			throw new IllegalArgumentException( "Channel must be positive." );

		this.channel = channel;
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		MultiValue rhs;
		
		if( !( obj instanceof MultiValue ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( MultiValue )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( channel, rhs.channel ).isEquals();
	}
	
	public int getChannel() {
		return channel;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 293, 601 )
			.appendSuper( super.hashCode() )
			.append( channel ).toHashCode();
	}
}
