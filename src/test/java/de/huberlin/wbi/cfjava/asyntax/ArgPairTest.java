package de.huberlin.wbi.cfjava.asyntax;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ArgPairTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		ArgPair ap;
		Alist<InParam> li;
		Amap<String, Alist<Expr>> bindMap;
		
		li = new Alist<>();
		bindMap = new Amap<>();
		
		ap = new ArgPair( li, bindMap );
		
		assertSame( li, ap.getInParamLst() );
		assertSame( bindMap, ap.getBindMap() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullInParamLstTest() {
		
		ArgPair ap;
		Amap<String, Alist<Expr>> bindMap;
		
		bindMap = new Amap<>();
		
		ap = new ArgPair( null, bindMap );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullBindMapTest() {
		
		ArgPair ap;
		Alist<InParam> li;
		
		li = new Alist<>();
		
		ap = new ArgPair( li, null );
	}
}
