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

public class Correl implements InParam {
	
	private final Alist<Name> nameLst;

	public Correl( final Alist<Name> nameLst ) {
		
		if( nameLst == null )
			throw new IllegalArgumentException( "Name list must not be null." );
		
		if( nameLst.size() <= 1 )
			throw new IllegalArgumentException( "Name list must have at least two elements." );
		
		this.nameLst = nameLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Correl rhs;
		
		if( !( obj instanceof Correl ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Correl )obj;
		
		return nameLst.equals( rhs.nameLst );
	}
	
	public Alist<Name> getNameLst() {
		return nameLst;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 73, 389 ).append( nameLst ).toHashCode();
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		
		buf.append( "{correl," );
		buf.append( nameLst.toString() );
		buf.append( '}' );
		
		return buf.toString();
	}

}
