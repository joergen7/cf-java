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

package de.huberlin.wbi.cfjava.parse;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ParseTriple {

	private final Alist<Expr> query;
	private final Amap<String, Alist<Expr>> rho;
	private final Amap<String, Lam> gamma;
	
	public ParseTriple( final Alist<Expr> query, final Amap<String, Alist<Expr>> rho, final Amap<String, Lam> gamma ) {
		
		if( query == null )
			throw new IllegalArgumentException( "Query expression list must not be null." );
		
		if( rho == null )
			throw new IllegalArgumentException( "Rho must not be null." );
		
		if( gamma == null )
			throw new IllegalArgumentException( "Gamma must not be null." );
		
		this.query = query;
		this.rho = rho;
		this.gamma = gamma;
	}

	public Alist<Expr> getQuery() {
		return query;
	}

	public Amap<String, Alist<Expr>> getRho() {
		return rho;
	}

	public Amap<String, Lam> getGamma() {
		return gamma;
	}
	
	@Override
	public String toString() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		
		buf.append( '{' ).append( query ).append( ',' ).append( rho )
			.append( ',' ).append( gamma ).append( '}' );
		
		return buf.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 421, 7 )
			.append( query )
			.append( rho )
			.append( gamma ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		ParseTriple rhs;
		
		if( !( obj instanceof ParseTriple ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( ParseTriple )obj;
		
		return new EqualsBuilder()
			.append( query, rhs.query )
			.append( rho, rhs.rho )
			.append( gamma, rhs.gamma ).isEquals();
		
		
	}

}
