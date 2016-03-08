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

public abstract class ContentHolder {

	private final String content;

	public ContentHolder( final String content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Content string must not be null." );
		
		this.content = content;
	}

	@Override
	public boolean equals( Object obj ) {
		
		ContentHolder rhs;
		
		if( !( obj instanceof ContentHolder ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ContentHolder )obj;
		
		return content.equals( rhs.content );
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 131, 23 ).append( content ).toHashCode();
	}

}
