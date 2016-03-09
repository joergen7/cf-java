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

public class Fut extends IdHolder implements LamNameHolder {
	
	private final Alist<Param> outLst;
	private final String lamName;

	public Fut( final String lamName, final int id, final Alist<Param> outLst ) {
		
		super( id );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( id <= 0 )
			throw new IllegalArgumentException( "Id must be positive." );
		
		if( outLst == null )
			throw new IllegalArgumentException( "Output parameter list must not be null." );
		
		this.lamName = lamName;
		this.outLst = outLst;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Fut rhs;
		
		if( !( obj instanceof Fut ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Fut )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( lamName, rhs.lamName )
			.append( outLst, rhs.outLst ).isEquals();
	}


	public Alist<Param> getOutLst() {
		return outLst;
	}
	
	@Override
	public String getLamName() {
		return lamName;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 443, 787 )
			.appendSuper( super.hashCode() )
			.append( lamName )
			.append( outLst ).toHashCode();
	}

	@Override
	public String toString() {
		return new StringBuffer().append( "{fut,\"" ).append( lamName )
			.append( "\"," ).append( getId() ).append( ',' ).append( outLst )
			.append( '}' ).toString();
	}
}
