package de.huberlin.wbi.cfjava.cuneiform;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Lang;
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
		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		
		reply = new Reply( id, ret, out, tstart, tdur );
		
		assertSame( id, reply.getId() );
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
		Amap<String, Alist<Expr>> fa;
		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );
		
		reply = new Reply( 0, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegIdTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		Amap<String, Alist<Expr>> fa;
		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );
		
		reply = new Reply( -12, ret, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRetMapTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		int id;
		Amap<String, Alist<Expr>> fa;
		
		tstart = 1457946567909L;
		tdur = 5L;
		out = new Alist<>();
		id = 1;
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );
		
		reply = new Reply( id, null, out, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullOutTest() {
		
		Reply reply;
		long tstart, tdur;
		Amap<String, Alist<Expr>> ret;
		int id;
		Amap<String, Alist<Expr>> fa;
		
		tstart = 1457946567909L;
		tdur = 5L;
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );
		
		reply = new Reply( id, ret, null, tstart, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegTstartTest() {
		
		Reply reply;
		long tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		int id;
		Amap<String, Alist<Expr>> fa;
		
		tdur = 5L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );

		reply = new Reply( id, ret, out, -1223L, tdur );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNegTdurTest() {
		
		Reply reply;
		long tstart, tdur;
		Alist<String> out;
		Amap<String, Alist<Expr>> ret;
		int id;
		Amap<String, Alist<Expr>> fa;
		Lam lam;
		Sign sign;
		ForBody body;
		
		tstart = 1457946567909L;
		out = new Alist<>();
		ret = new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) );
		id = 1;
		fa = new Amap<String, Alist<Expr>>().put( "person", new Alist<Expr>().add( new Str( "Jorgen" ) ) );
		
		reply = new Reply( id, ret, out, tstart, -5 );
	}	
	
	
	
	
	
	

	@Test
	public void contructorFromStringTest() {
		
		String replyStr;
		Reply reply;
		
		replyStr = "#{arg => #{\"person\" => [{str,\"Jorgen\"}]},"
			+"  id => 1,"
			+"  lam => {lam,1,\"greet\","
			+"    {sign,[{param,{name,\"out\",false},false}],"
			+"          [{param,{name,\"person\",false},false}]},"
			+"    {forbody,bash,\"\\n  out=\\\"Hello $person\\\"\\n\"}},"
			+"  out => [],"
			+"  ret => #{\"out\" => [{str,\"Hello Jorgen\"}]},"
			+"  tdur => 5,"
			+"  tstart => 1457946567909}";
		
		// reply = new Reply( replyStr );
	}
}
