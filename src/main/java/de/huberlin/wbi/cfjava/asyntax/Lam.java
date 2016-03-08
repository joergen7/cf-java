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

public class Lam extends SrcLocated implements LamSurrogate, LamNameHolder {

	private final Body body;
	private final String lamName;
	private final Sign sign;
	
	public Lam( final int line, final String lamName, final Sign sign, final Body body ) {
		
		super( line );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( sign == null )
			throw new IllegalArgumentException( "Signature must not be null." );
		
		if( body == null )
			throw new IllegalArgumentException( "Body must not be null." );
		
		this.lamName = lamName;
		this.sign = sign;
		this.body = body;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Lam rhs;
		
		if( !( obj instanceof Lam ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Lam )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( obj ) )
			.append( lamName, rhs.lamName )
			.append( sign, rhs.sign )
			.append( body, rhs.body ).isEquals();		
	}

	public Body getBody() {
		return body;
	}

	@Override
	public String getLamName() {
		return lamName;
	}
	
	public Sign getSign() {
		return sign;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 569, 967 )
			.appendSuper( super.hashCode() )
			.append( lamName )
			.append( sign )
			.append( body ).toHashCode();
	}

}
