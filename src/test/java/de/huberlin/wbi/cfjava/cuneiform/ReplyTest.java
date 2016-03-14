package de.huberlin.wbi.cfjava.cuneiform;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ReplyTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievableTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		int id;
		Alist<Param> lo;
		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );
		
		
		reply = new Reply( id, lo, ret, out, tstart, tdur );
		
		assertSame( id, reply.getId() );
		assertSame( lo, reply.getOutLst() );
		assertSame( ret, reply.getRetMap() );
		assertSame( out, reply.getOut() );
		assertEquals( tstart, reply.getTstart() );
		assertEquals( tdur, reply.getTdur() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnZeroIdTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		Alist<Param> lo;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );

		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		
		reply = new Reply( 0, lo, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		Alist<Param> lo;

		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );

		
		reply = new Reply( -12, lo, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOutLstTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		Alist<Param> lo;

		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		
		reply = new Reply( 1, null, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnEmptyOutLstTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		Alist<Param> lo;
		lo = new Alist<>();

		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		
		reply = new Reply( 1, lo, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRetMapTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		int id;
		Alist<Param> lo;

		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		id = 1;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );

		
		reply = new Reply( id, lo, null, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOutTest() {
		
		Reply reply;
		long tstart, tdur;
		Amap<String, Alist<Expr>> ret;
		int id;
		Alist<Param> lo;

		
		tstart = 1457946567909L;
		tdur = 5L;
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );

		
		reply = new Reply( id, lo, ret, null, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegTstartTest() {
		
		Reply reply;
		long tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		int id;
		Alist<Param> lo;

		
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );


		reply = new Reply( id, lo, ret, out, -1223L, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegTdurTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		int id;
		Lam lam;
		Sign sign;
		ForBody body;
		Alist<Param> lo;

		
		tstart = 1457946567909L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		lo = new Alist<Param>().add( new Param( new Name( "out", false ), false ) );

		
		reply = new Reply( id, lo, ret, out, tstart, -5 );
	}	
}
