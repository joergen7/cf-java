package de.huberlin.wbi.cfjava.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlistTest {

	@SuppressWarnings("static-method")
	@Test
	public void usortRemovesDuplicateElements() {

		Alist<String> template, unique;

		template = new Alist<>();
		template = template.add( "A" ).add( "B" ).add( "B" ).add( "C" );
		assertEquals( 4, template.size() );

		unique = template.usort();

		assertNotSame( template, unique );
		assertEquals( 4, template.size() );
		assertEquals( 3, unique.size() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void reverseListWorks() {

		Alist<String> template, expected;

		template = new Alist<>();
		template = template.add( "C" ).add( "B" ).add( "A" );

		expected = new Alist<>();
		expected = expected.add( "A" ).add( "B" ).add( "C" );

		assertEquals( expected, template.reverse() );
	}

	@SuppressWarnings("static-method")
	@Test
	public void mapWorks() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l1 = l1.add( 1 ).add( 2 ).add( 3 );

		l2 = new Alist<>();
		l2 = l2.add( 2 ).add( 3 ).add( 4 );

		assertEquals( l2, l1.map( ( i ) -> i + 1 ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void emptyListEqualsNullReturnsFalse() {

		Alist<Integer> l;

		l = new Alist<>();
		assertFalse( l.equals( null ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void emptyListEqualsOtherClassShouldReturnFalse() {

		Alist<Integer> l;

		l = new Alist<>();

		assertFalse( l.equals( 4 ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void listsOfDifferingSizeShouldNotBeEqual() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l1 = l1.add( 1 );

		l2 = new Alist<>();
		l2 = l2.add( 1 ).add( 2 );

		assertNotEquals( l1, l2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void listsWithEqualElementsShouldBeEqual() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l1 = l1.add( 1 ).add( 2 );

		l2 = new Alist<>();
		l2 = l2.add( 1 ).add( 2 );

		assertEquals( l1, l2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void listsWithDifferingElementsShouldNotBeEqual() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l1 = l1.add( 1 ).add( 3 );

		l2 = new Alist<>();
		l2 = l2.add( 1 ).add( 2 );

		assertNotEquals( l1, l2 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void nonEmptyListEqualsNullReturnsFalse() {

		Alist<Integer> l;

		l = new Alist<>();
		l = l.add( 1 );

		assertFalse( l.equals( null ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void nonEmptyListEqualsOtherClassReturnsFalse() {

		Alist<Integer> l;

		l = new Alist<>();
		l = l.add( 1 );

		assertFalse( l.equals( 4 ) );
	}

	@SuppressWarnings("static-method")
	@Test
	public void emptyListShouldBeEqualToEmptyList() {

		Alist<Integer> a, b;

		a = new Alist<>();
		b = new Alist<>();

		assertEquals( a, b );
	}

	@SuppressWarnings("static-method")
	@Test
	public void appendConcatenatesTwoLists() {

		Alist<Integer> l1, l2, l3, x;

		l1 = new Alist<>();
		l1 = l1.add( 1 );

		l2 = new Alist<>();
		l2 = l2.add( 2 );

		l3 = l1.append( l2 );

		x = new Alist<>();
		x = x.add( 2 ).add( 1 );

		assertEquals( x, l3 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void appendToEmptyListReturnsOriginal() {

		Alist<Integer> l1, l2, l3;

		l1 = new Alist<>();

		l2 = new Alist<>();
		l2 = l2.add( 2 );

		l3 = l1.append( l2 );
		assertEquals( l2, l3 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void appendEmptyListShouldReturnOriginal() {

		Alist<Integer> l1, l2, l3;

		l1 = new Alist<>();
		l1 = l1.add( 1 );

		l2 = new Alist<>();

		l3 = l1.append( l2 );

		assertEquals( l1, l3 );
	}

	@SuppressWarnings("static-method")
	@Test
	public void flatMapShouldWork() {

		Alist<Integer> l1, l2, y;

		l1 = new Alist<>();
		l1 = l1.add( 4 ).add( 3 ).add( 2 ).add( 1 );

		l2 = l1.flatMap( ( Integer elem ) -> {
			Alist<Integer> x = new Alist<>();
			return x.add( elem + 1 ).add( elem );
		} );
		
		y = new Alist<>();
		y = y.add( 5 ).add( 4 ).add( 4 ).add( 3 ).add( 3 ).add( 2 ).add( 2 ).add( 1 );
		
		assertEquals( y, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IndexOutOfBoundsException.class )
	public void nthZeroOnNonEmptyListShouldThrowIae() {
		
		Alist<Integer> l;
		
		l = new Alist<>();
		l = l.add( 1 );
		
		l.nth( 0 );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IndexOutOfBoundsException.class )
	public void nthZeroOnEmptyListShouldThrowIae() {
		
		Alist<Integer> l;
		
		l = new Alist<>();
		
		l.nth( 0 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nthShouldWork() {
		
		Alist<Integer> l;
		
		l = new Alist<>();
		l = l.add( 15 ).add( 20 );
		
		assertEquals( Integer.valueOf( 15 ), l.nth( 2 ) );	
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void foldlShouldWork() {
		
		Alist<Integer> l;
		int sum;
		
		l = new Alist<>();
		l = l.add( 4 ).add( 3 ).add( 2 ).add( 1 );
		
		sum = l.foldl( ( Integer elem, Integer s ) -> s+elem, 0 );
		
		assertEquals( 10, sum );
	}
}
