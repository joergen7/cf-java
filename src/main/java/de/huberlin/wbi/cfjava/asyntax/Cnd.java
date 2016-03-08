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

public class Cnd extends SrcLocated implements Expr {

	private final Alist<Expr> condLst;
	private final Alist<Expr> thenLst;
	private final Alist<Expr> elseLst;
	
	public Cnd( final int line, final Alist<Expr> condLst, final Alist<Expr> thenLst, final Alist<Expr> elseLst ) {
		
		super( line );
		
		if( condLst == null )
			throw new IllegalArgumentException( "Condition expression list must not be null." );

		if( thenLst == null )
			throw new IllegalArgumentException( "Then expression list must not be null." );

		if( elseLst == null )
			throw new IllegalArgumentException( "Else expression list must not be null." );

		this.condLst = condLst;
		this.thenLst = thenLst;
		this.elseLst = elseLst;
	}

	public Alist<Expr> getCondLst() {
		return condLst;
	}

	public Alist<Expr> getThenLst() {
		return thenLst;
	}

	public Alist<Expr> getElseLst() {
		return elseLst;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 59, 677 )
			.appendSuper( super.hashCode() )
			.append( condLst )
			.append( thenLst )
			.append( elseLst ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		Cnd rhs;
		
		if( !( obj instanceof Cnd ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Cnd )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( condLst, rhs.condLst )
			.append( thenLst, rhs.thenLst )
			.append( elseLst, rhs.elseLst ).isEquals();
	}
}
