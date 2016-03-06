package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Amap;

public class FutTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", 435, fileMap );
		
		assertEquals( "f", f.getLamName() );
		assertEquals( 435, f.getId() );
		assertSame( fileMap, f.getFileMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullLamNameTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( null, 435, fileMap );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyLamNameTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "", 435, fileMap );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", -12, fileMap );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", 0, fileMap );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullFileMapTest() {
		
		Fut f;
		
		f = new Fut( "f", 435, null );		
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsNullTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", 435, fileMap );
		
		assertNotEquals( f, null );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsStringTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", 435, fileMap );
		
		assertNotEquals( f, "blub" );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsItselfTest() {
		
		Fut f;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f = new Fut( "f", 435, fileMap );
		
		assertEquals( f, f );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void equalsIdenticalInstanceTest() {
		
		Fut f1, f2;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f1 = new Fut( "f", 435, fileMap );
		f2 = new Fut( "f", 435, fileMap );
		
		assertEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentLamNameTest() {
		
		Fut f1, f2;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f1 = new Fut( "f", 435, fileMap );
		f2 = new Fut( "g", 435, fileMap );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentIdTest() {
		
		Fut f1, f2;
		Amap<String,Boolean> fileMap;
		
		fileMap = new Amap<>();
		
		f1 = new Fut( "f", 435, fileMap );
		f2 = new Fut( "f", 436, fileMap );
		
		assertNotEquals( f1, f2 );
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void notEqualsIfDifferentFileMapTest() {
		
		Fut f1, f2;
		Amap<String,Boolean> fileMap1, fileMap2;
		
		fileMap1 = new Amap<>();
		fileMap2 = new Amap<String, Boolean>().put( "blub", false );
		
		f1 = new Fut( "f", 435, fileMap1 );
		f2 = new Fut( "f", 435, fileMap2 );
		
		assertNotEquals( f1, f2 );
	}
}
