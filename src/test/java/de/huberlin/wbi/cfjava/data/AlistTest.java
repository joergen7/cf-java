package de.huberlin.wbi.cfjava.data;

import static org.junit.Assert.*;

import java.util.function.Predicate;

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
	
	@Test
	public void allOnEmptyListAlwaysReturnsTrueTest() {
		assertTrue( new Alist<Integer>().all( new AlwaysFalse() ) );
	}
	
	@Test
	public void allOnSingleElementListReturnsResultTest() {
		
		Predicate<Integer> pred;
		Alist<Integer> list;
		
		pred = new AlwaysFalse();
		list = new Alist<Integer>().add( 5 );
		
		assertFalse( list.all( pred ) );
	}
	
	@Test
	public void allOnMultiElementListReturnsTrueWhenAllTrueTest() {
		
		Predicate<Integer> pred;
		Alist<Integer> list;
		
		pred = new TrueOnFive();
		list = new Alist<Integer>().add( 5 ).add( 5 );
		
		assertTrue( list.all( pred ) );
	}
	
	@Test
	public void allOnMultiElementListReturnsFalseWhenFirstIsFalseTest() {
		
		Predicate<Integer> pred;
		Alist<Integer> list;
		
		pred = new TrueOnFive();
		list = new Alist<Integer>().add( 5 ).add( 4 );
		
		assertFalse( list.all( pred ) );
	}
	
	@Test
	public void allOnMultiElementListReturnsFalseWhenLastIsFalseTest() {
		
		Predicate<Integer> pred;
		Alist<Integer> list;
		
		pred = new TrueOnFive();
		list = new Alist<Integer>().add( 4 ).add( 5 );
		
		assertFalse( list.all( pred ) );
	}
	
	/**********/
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyListNotEqualNullTest() {

		Alist<Integer> l;

		l = new Alist<>();

		assertNotEquals( l, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonEmptyListNotEqualNullTest() {

		Alist<Integer> l;

		l = new Alist<Integer>().add( 4 );

		assertNotEquals( l, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyListNotEqualStringTest() {

		Alist<Integer> l;

		l = new Alist<>();

		assertNotEquals( l, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonemptyListNotEqualStringTest() {

		Alist<Integer> l;

		l = new Alist<Integer>().add( 4 );

		assertNotEquals( l, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyListEqualsItselfTest() {

		Alist<Integer> l;

		l = new Alist<>();

		assertEquals( l, l );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonemptyListEqualsItselfTest() {

		Alist<Integer> l;

		l = new Alist<Integer>().add( 4 );

		assertEquals( l, l );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void emptyListEqualsOtherEmptyListTest() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l2 = new Alist<>();

		assertEquals( l1, l2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nonEmptyListEqualsIdenticalListTest() {

		Alist<Integer> l1, l2;

		l1 = new Alist<Integer>().add( 4 );
		l2 = new Alist<Integer>().add( 4 );

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
	public void listsOfDifferingSizeShouldNotBeEqual() {

		Alist<Integer> l1, l2;

		l1 = new Alist<>();
		l1 = l1.add( 1 );

		l2 = new Alist<>();
		l2 = l2.add( 1 ).add( 2 );

		assertNotEquals( l1, l2 );
	}

	/***********/
	
	
	
	public class AlwaysFalse implements Predicate<Integer> {
		
		@Override
		public boolean test( Integer i ) { return false; }
	}
	
	public class TrueOnFive implements Predicate<Integer> {
		
		@Override
		public boolean test( Integer i ) { return i == 5; }
	}
}
