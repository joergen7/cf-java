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

public class Sign {

	private final Alist<InParam> inLst;
	private final Alist<Param> outLst;
	
	public Sign( final Alist<Param> outLst, final Alist<InParam> inLst ) {
		
		if( outLst == null )
			throw new IllegalArgumentException( "Output parameter list must not be null." );
		
		if( outLst.isEmpty() )
			throw new IllegalArgumentException( "Output parameter list must not be empty." );
		
		if( inLst == null )
			throw new IllegalArgumentException( "Input parameter list must not be null." );
		
		this.outLst = outLst;
		this.inLst = inLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Sign rhs;
		
		if( !( obj instanceof Sign ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Sign )obj;
		
		return new EqualsBuilder()
			.append( outLst, rhs.outLst )
			.append( inLst, rhs.inLst ).isEquals();
	}

	public Alist<InParam> getInLst() {
		return inLst;
	}
	
	public Alist<Param> getOutLst() {
		return outLst;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 701 )
			.append( outLst )
			.append( inLst ).toHashCode();
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append( "{sign," ).append( outLst )
			.append( ',' ).append( inLst ).append( '}' ).toString();
	}
}
