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
}
