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

package de.huberlin.wbi.cfjava.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Amap<K,V> {

	private final Map<K,V> content;
	
	public Amap( K key, V value ) {
		
		Map<K,V> m;
		
		if( key == null )
			throw new IllegalArgumentException( "Key term must not be null." );
		
		if( value == null )
			throw new IllegalArgumentException( "Value term must not be null." );
		
		m = new HashMap<>();
		m.put( key,  value );
		
		content = Collections.unmodifiableMap( m );
	}
	
	public Amap() {
		content = new HashMap<>();
	}
	
	private Amap( HashMap<K,V> content ) {
		this.content = Collections.unmodifiableMap( content ); 
	}
	
	public Amap<K,V> put( K key, V value ) {
		
		HashMap<K,V> newContent;
		
		if( key == null )
			throw new IllegalArgumentException( "Key term must not be null." );
		
		if( value == null )
			throw new IllegalArgumentException( "Value term must not be null." );
		
		newContent = new HashMap<>();
		newContent.putAll( content );
		newContent.put( key, value );
		
		return new Amap<>( newContent );
	}
	
	public V get( K key ) {
		
		V value;
		
		if( key == null )
			throw new IllegalArgumentException( "Key must not be null." );
				
		value = content.get( key );
		
		if( value == null )
			throw new RuntimeException( "The key "+key+" is unbound." );
		
		return value;
	}
	
	public V get( K key, V def ) {
		
		if( key == null )
			throw new IllegalArgumentException( "Key term must not be null." );
		
		if( def == null )
			throw new IllegalArgumentException( "Default term must not be null." );
		
		if( content.containsKey( key ) )
			return content.get( key );
		
		return def;
	}
	
	public Amap<K,V> merge( Amap<K,V> tm2 ) {
		
		HashMap<K,V> newContent;
		
		if( tm2 == null )
			throw new IllegalArgumentException( "Other term map must not be null." );
		
		newContent = new HashMap<>();
		newContent.putAll( content );
		newContent.putAll( tm2.content );
		
		return new Amap<>( newContent );		
	}
	
	public int size() {
		return content.size();
	}
	
	public boolean isEmpty() {
		return content.isEmpty();
	}

	public Set<K> keys() {
		
		HashSet<K> s;
		
		s = new HashSet<>();
		s.addAll( content.keySet() );
		
		return s;
	}

	@Override
	public String toString() {
		
		StringBuffer buf;
		boolean comma;
		
		buf = new StringBuffer();
		
		buf.append( "#{" );
		
		comma = false;
		for( K key : content.keySet() ) {
			
			if( comma )
				buf.append( ',' );
			comma = true;
			
			buf.append( key ).append( "=>" ).append( content.get( key ) );
		}
		
		buf.append( '}' );
		
		return buf.toString();
	}

	public boolean isKey( K key ) {
		return content.containsKey( key );
	}

	public Alist<V> values() {
		
		Alist<V> acc;
		
		acc = new Alist<>();
		for( V v : content.values() )
			acc = acc.add( v );
		
		return acc;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 157, 211 ).append( content ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		Amap<?, ?> rhs;
		
		if( !( obj instanceof Amap ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Amap<?, ?> )obj;
		
		return content.equals( rhs.content );
	}
}
