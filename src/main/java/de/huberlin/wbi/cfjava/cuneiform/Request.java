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
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Request {
	
	private final Lam lam;
	private final Amap<String, Alist<Expr>> bindMap;

	public Request( final Lam lam, final Amap<String, Alist<Expr>> bindMap ) {
		
		if( lam == null )
			throw new IllegalArgumentException( "Lambda must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lam = lam;
		this.bindMap = bindMap;
	}

	public Lam getLam() {
		return lam;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 733, 101 )
			.append( lam )
			.append( bindMap ).toHashCode();
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
	
	@Override
	public String toString() {
		return new StringBuffer().append( '{' ).append( lam ).append( ',' )
			.append( bindMap ).append( '}' ).toString();
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
	
	public String getEffiConf( String dir ) {
		
		StringBuffer buf;
		Sign sign;
		ForBody body;
		String lang;
		String lamName;
		boolean comma;
		Alist<Param> lo;
		Alist<InParam> li;
		Param p;
		String label;
		Str str;
		
		sign = lam.getSign();
		lo = sign.getOutLst();
		li = sign.getInLst();
		body = ( ForBody )lam.getBody();
		lang = body.getLang().toString().toLowerCase();
		lamName = lam.getLamName();
		
		buf = new StringBuffer();

		buf.append( "#{prefix=>undef,dir=>\"" ).append( dir )
			.append( "\",lang=>" ).append( lang ).append( ",taskname=>\"" )
			.append( lamName ).append( "\",outlist=>[" );
		
		comma = false;
		for( Param param : lo ) {
			
			if( comma )
				buf.append( ',' );
			comma = true;
			
			buf.append( '"' ).append( param.getName().getLabel() )
				.append( '"' );
		}
		
		buf.append( "],inmap=>#{" );
		
		for( InParam inParam : li ) {
			
			p = ( Param )inParam;
			label = p.getName().getLabel();
			buf.append( '"' ).append( label ).append( "\"=>[" );
			
			comma = false;
			for( Expr expr : bindMap.get( label ) ) {
				
				str = ( Str )expr;
				
			}
		}
		
		buf.append( "},lmap=>#{" );
		
		// TODO: append lmap
		
		buf.append( "},fmap=>#{" );
		
		// TODO: append fmap
		
		buf.append( "}}.\n" );
		
		
		return buf.toString();
	}
}
