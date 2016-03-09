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

public class Str extends ContentHolder implements Expr {
	
	public Str( final String content ) {
		super( content );
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		if( !( obj instanceof Str ) )
			return false;
		
		if( obj == this )
			return true;
		
		return super.equals( obj );
	}
		
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 863, 389 )
			.appendSuper( super.hashCode() ).toHashCode();
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append( "{str,\"" ).append( getContent() )
			.append( "\"}" ).toString();
	}
}
