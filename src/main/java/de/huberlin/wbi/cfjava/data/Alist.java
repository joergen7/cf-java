package de.huberlin.wbi.cfjava.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Alist<T> implements Iterable<T> {

	private class AlistIterator implements Iterator<T> {

		private Alist<T> cursor;

		AlistIterator( Alist<T> template ) {
			cursor = template;
		}

		@Override
		public boolean hasNext() {
			return !cursor.isEmpty();
		}

		@Override
		public T next() {

			T elem;

			elem = cursor.hd();
			cursor = cursor.tl();

			return elem;
		}
	}

	private class Cons extends Alist<T> {

		private final T hd;
		private final Alist<T> tl;

		Cons( T hd, Alist<T> tl ) {

			this.hd = hd;
			this.tl = tl;
		}
		
		@Override
		public boolean all( Predicate<T> pred ) {
			
			if( pred.test( hd ) )
				return tl.all( pred );
			
			return false;
		}

		@Override
		public Alist<T> append( Alist<T> l2 ) {

			if( l2.isEmpty() )
				return this;

			return new Cons( hd, tl.append( l2 ) );
		}

		@Override
		public boolean equals( Object obj ) {

			Alist<?> other;

			if( obj == null )
				return false;

			if( !( obj instanceof Alist ) )
				return false;

			other = ( Alist<?> )obj;

			if( size() != other.size() )
				return false;

			if( !hd.equals( other.hd() ) )
				return false;

			return tl.equals( other.tl() );
		}

		@Override
		public Alist<T> flatMap( Function<T, Alist<T>> fn ) {
			return fn.apply( hd ).append( tl.flatMap( fn ) );
		}
		
		@Override
		public <X> X foldl( BiFunction<T,X,X> fn, X acc0 ) {			
			return tl.foldl( fn, fn.apply( hd, acc0 ) );
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
				.appendSuper( super.hashCode() )
				.append( hd )
				.append( tl ).toHashCode();
		}

		@Override
		public T hd() {
			return hd;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public Alist<T> map( Function<T, T> fn ) {
			return new Cons( fn.apply( hd ), tl.map( fn ) );
		}
		
		@Override
		public T nth( int i ) {
			
			if( i < 1 )
				throw new IndexOutOfBoundsException( "Index out of bounds." );
			
			if( i == 1 )
				return hd;
			
			return tl.nth( i-1 );
		}

		@Override
		public int size() {
			return 1 + tl.size();
		}

		@Override
		public Alist<T> tl() {
			return tl;
		}

		@Override
		public String toString() {

			StringBuffer buf;

			buf = new StringBuffer();

			buf.append( '[' ).append( hd );

			if( tl.isEmpty() )
				buf.append( ']' );
			else
				buf.append( ',' ).append( tl.toString().substring( 1 ) );

			return buf.toString();
		}
		
	}

	public Alist<T> add( T hd ) {
		return new Cons( hd, this );
	}

	@SuppressWarnings("unused")
	public boolean all( Predicate<T> pred ) {
		return true;
	}

	public Alist<T> append( Alist<T> l2 ) {
		return l2;
	}

	@Override
	public boolean equals( Object obj ) {

		Alist<?> rhs;
		
		if( !( obj instanceof Alist ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Alist<?> )obj;

		return rhs.isEmpty();
	}

	@SuppressWarnings("unused")
	public Alist<T> flatMap( Function<T, Alist<T>> fn ) {
		return this;
	}
	
	@SuppressWarnings("unused")
	public <X> X foldl( BiFunction<T, X, X> fn, X acc0 ) {
		return acc0;	
	}

	@Override
	public int hashCode() {
		return 379;
	}

	public T hd() {
		throw new UnsupportedOperationException( "Cannot retrieve head from nil." );
	}

	@SuppressWarnings("static-method")
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new AlistIterator( this );
	}

	@SuppressWarnings("unused")
	public Alist<T> map( Function<T, T> fn ) {
		return this;
	}

	@SuppressWarnings("unused")
	public T nth( int i ) {
		throw new IndexOutOfBoundsException( "Index out of bounds." );
	}

	public Alist<T> reverse() {

		Alist<T> result;
		Alist<T> trav;

		trav = this;
		result = new Alist<>();

		while (!trav.isEmpty()) {
			result = result.add( trav.hd() );
			trav = trav.tl();
		}

		return result;
	}

	@SuppressWarnings("static-method")
	public int size() {
		return 0;
	}

	public Alist<T> tl() {
		throw new UnsupportedOperationException( "Cannot retrieve tail from nil." );
	}

	@Override
	public String toString() {
		return "[]";
	}

	public Alist<T> usort() {

		Alist<T> result;
		Alist<T> trav;
		Set<T> elementSet;

		elementSet = new HashSet<>();
		trav = this;

		while (!trav.isEmpty()) {
			elementSet.add( trav.hd() );
			trav = trav.tl();
		}

		result = new Alist<>();

		for( T element : elementSet )
			result = result.add( element );

		return result;
	}
}
