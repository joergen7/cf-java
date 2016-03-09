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

public class Name implements LabelHolder {

	private final boolean isFile;
	private final String label;
	
	public Name( final String label, final boolean isFile ) {
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
		this.isFile = isFile;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Name rhs;
		
		if( !( obj instanceof Name ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Name )obj;
		
		return new EqualsBuilder()
			.append( label, rhs.label )
			.append( isFile, rhs.isFile ).isEquals();
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 883, 347 )
			.append( label )
			.append( isFile ).toHashCode();
	}
	
	public boolean isFile() {
		return isFile;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append( "{name,\"" ).append( label )
			.append( "\"," ).append( isFile ).append( '}' ).toString();
	}
}
