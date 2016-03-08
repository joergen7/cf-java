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

public class Var extends SrcLocated implements LamSurrogate, Expr, LabelHolder {
	
	private final String label;

	public Var( final int line, final String label ) {
		
		super( line );
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Var rhs;
		
		if( !( obj instanceof Var ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Var )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( label, rhs.label ).isEquals();
	}
	
	@Override
	public String getLabel() {
		return label;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 563, 11 )
			.appendSuper( super.hashCode() )
			.append( label ).toHashCode();
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		
		buf.append( "{var," ).append( getLine() ).append( ',' ).append( label )
			.append( '}' );
		
		return buf.toString();
	}
}
