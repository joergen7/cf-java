package de.huberlin.wbi.cfjava.data;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Amap<K,V> {

	private final HashMap<K,V> content;
	
	public Amap( K key, V value ) {
		this();
		
		if( key == null )
			throw new IllegalArgumentException( "Key term must not be null." );
		
		if( value == null )
			throw new IllegalArgumentException( "Value term must not be null." );
		
		content.put( key, value );
	}
	
	public Amap() {
		content = new HashMap<>();
	}
	
	private Amap( HashMap<K,V> content ) {
		this.content = content;
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
		return content.keySet();
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
