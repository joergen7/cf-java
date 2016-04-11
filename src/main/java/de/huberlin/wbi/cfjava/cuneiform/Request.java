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

package de.huberlin.wbi.cfjava.cuneiform;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.IdHolder;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Request extends IdHolder {
	
	private final Amap<String, Alist<Expr>> bindMap;
	private final Lam lam;

	public Request( final Lam lam, final Amap<String, Alist<Expr>> bindMap, int id ) {
		
		super( id );
		
		if( lam == null )
			throw new IllegalArgumentException( "Lambda must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lam = lam;
		this.bindMap = bindMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		Request rhs;
		
		if( !( obj instanceof Request ) )
			return false;

		if( obj == this )
			return true;
		
		rhs = ( Request )obj;
		
		return new EqualsBuilder()
			.append( lam, rhs.lam )
			.append( bindMap, rhs.bindMap ).isEquals();
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	public Lam getLam() {
		return lam;
	}
	
	public Set<String> getStageInFilenameSet() {
		
		Set<String> filenameSet;
		Sign sign;
		Alist<InParam> li;
		Param param;
		String label;
		Str str;
		Name name;
		
		sign = lam.getSign();
		li = sign.getInLst();
		
		filenameSet = new HashSet<>();
		for( InParam inParam : li ) {
			
			param = ( Param )inParam;
			name = param.getName();
			
			if( !name.isFile() )
				continue;
			
			label = name.getLabel();
			
			for( Expr expr : bindMap.get( label ) ) {
				
				str = ( Str )expr;	
				filenameSet.add( str.getContent() );
			}
		}
		
		return filenameSet;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 733, 101 )
			.append( lam )
			.append( bindMap ).toHashCode();
	}
	

	@Override
	public String toString() {
		
		StringBuffer buf;
		boolean comma;
		
		buf = new StringBuffer().append( '{' ).append( lam ).append( ",#{" );
		
		comma = false;
		for( String n : bindMap.keys() ) {
			
			if( comma )
				buf.append( ',' );
			comma = true;
			
			buf.append( '"' ).append( n ).append( "\"=>" )
				.append( bindMap.get( n ) );
		}
			
		buf.append( "}," ).append( getId() ).append( ",#{}}." );
		
		return buf.toString();
	}
}
