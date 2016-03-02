package de.huberlin.wbi.cfjava.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class AmapTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsEmptyMap() {
		
		Amap<Integer,String> m;
		
		m = new Amap<>();
		assertTrue( m.isEmpty() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorReturnsMapWithSingleEntry() {
		
		Amap<Integer,String> m;
		int key;
		String value;
		
		key = 1;
		value = "bla";
		
		m = new Amap<>( key, value );
		assertEquals( 1, m.size() );
		assertEquals( value, m.get( key ) );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullKey() {
		new Amap<Integer,String>( null, "bla" );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructThrowsIaeOnNullValue() {
		new Amap<Integer,String>( 2, null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullShouldThrowIae() {
		
		Amap<Integer,String> m;
		
		m = new Amap<>();
		m.get( null );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=RuntimeException.class )
	public void getUnboundKeyShouldThrowUve() {
		
		Amap<Integer,String> m;
		
		m = new Amap<>();
		m.get( 2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void getUnboundKeyWithDefaultShouldReturnDefault() {
		
		Amap<Integer,String> m;
		String def;
		
		m = new Amap<>();
		def = "blub";
		
		assertEquals( def, m.get( 5, def ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getNullWithDefaultShouldThrowIae() {
		
		Amap<Integer,String> m;
		String def;
		
		m = new Amap<>();
		def = "blub";
		
		m.get( null, def );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void getWithNullDefaultShouldThrowIae() {
		
		Amap<Integer,String> m;
		
		m = new Amap<>();
		m.get( 1, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeCombinesTwoMaps() {
		
		Amap<Integer,String> m1, m2, m3;
		int key1, key2;
		String value1, value2;
		
		key1 = 1;
		key2 = 2;
		value1 = "bla";
		value2 = "blub";
		
		m1 = new Amap<>( key1, value1 );
		m2 = new Amap<>( key2, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 2, m3.size() );
		assertEquals( value1, m3.get( key1 ) );
		assertEquals( value2, m3.get( key2 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeSecondShouldSupersedeFirst() {
		
		Amap<Integer,String> m1, m2, m3;
		int key;
		String value1, value2;
		
		key = 1;
		value1 = "bla";
		value2 = "blub";
		
		m1 = new Amap<>( key, value1 );
		m2 = new Amap<>( key, value2 );
		
		m3 = m1.merge( m2 );
		assertEquals( 1, m3.size() );
		assertEquals( value2, m3.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void mergeLeavesBothOriginalMapsUnchanged() {
		
		Amap<Integer,String> tm1, tm2, tm3;
		
		tm1 = new Amap<>( 1, "bla" );
		assertEquals( 1, tm1.size() );
		
		tm2 = new Amap<>( 2, "blub" );
		assertEquals( 1, tm2.size() );
		
		tm3 = tm1.merge( tm2 );
		assertEquals( 1, tm1.size() );
		assertEquals( 1, tm2.size() );
		assertEquals( 2, tm3.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void mergeWithNullShouldThrowIae() {
		
		Amap<Integer,String> tm;
		
		tm = new Amap<>();
		tm.merge( null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putLeavesOriginalMapUnchanged() {
		
		Amap<Integer,String> tm1, tm2;
		
		tm1 = new Amap<>( 1, "bla" );
		assertEquals( 1, tm1.size() );
		
		tm2 = tm1.put( 2, "blub" );
		assertEquals( 1, tm1.size() );
		assertEquals( 2, tm2.size() );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void putReturnsNewMapWithNewEntry() {
		
		Amap<Integer,String> m1, m2;
		int key;
		String value;
		
		key = 1;
		value = "bla";
		
		
		m1 = new Amap<>();
		assertTrue( m1.isEmpty() );
		
		m2 = m1.put( key, value );
		assertTrue( m1.isEmpty() );
		assertEquals( 1, m2.size() );
		assertEquals( value, m2.get( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullKey() {
		
		Amap<Integer,String> tm;
		
		tm = new Amap<>();
		tm.put( null, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=IllegalArgumentException.class )
	public void putThrowsIaeOnNullValue() {
		
		Amap<Integer,String> tm;
		
		tm = new Amap<>();
		tm.put( 2, null );
	}
	
	
	
	
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsTrueOnExistingKey() {
		
		Amap<Integer,String> tm;
		int key;
		
		key = 2;
		
		tm = new Amap<>( key, "blub" );
		assertTrue( tm.isKey( key ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void hasKeyReturnsFalseOnNonExistingKey() {
		
		Amap<Integer,String> tm;
		tm = new Amap<>( 4, "bla" );
		assertFalse( tm.isKey( 3 ) );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void valuesReturnsAlistOfValues() {
		
		Amap<Integer,String> tm;
		Alist<String> v;
		String s;
		
		tm = new Amap<>();
		tm = tm.put( 1, "bla" ).put( 2, "blub" );
		
		v = tm.values();
		assertEquals( 2, v.size() );
		s = v.hd();
		assertTrue( s.equals( "bla" ) || s.equals( "blub" ) );
		s = v.tl().hd();
		assertTrue( s.equals( "bla" ) || s.equals( "blub" ) );
		
	}
	
}
