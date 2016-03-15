package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class EffiListenerTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void contructorFromStringTest() throws IOException {
		
		String script;
		ANTLRInputStream input;
		EffiLexer lexer;
		CommonTokenStream tokenStream;
		EffiParser parser;
		ReplyListener rv;
		ParseTree tree;
		ParseTreeWalker walker;
		
		script = "#{arg => #{\"person\" => [{str,\"Jorgen\"}]},\n"
			+"  id => 1,\n"
			+"  lam => {lam,1,\"greet\",\n"
			+"    {sign,[{param,{name,\"out\",false},false}],\n"
			+"          [{param,{name,\"person\",false},false}]},\n"
			+"    {forbody,bash,\"\\n  out=\\\"Hello $person\\\"\\n\"}},\n"
			+"  out => [],\n"
			+"  ret => #{\"out\" => [{str,\"Hello Jorgen\"}]},\n"
			+"  tdur => 5,\n"
			+"  tstart => 1457946567909}.\n";
				
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new EffiLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new EffiParser( tokenStream );
			rv = new ReplyListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			walker.walk( rv, tree );
			
			assertEquals( 1, rv.getId() );
			assertEquals( 1457946567909L, rv.getTstart() );
			assertEquals( 5, rv.getTdur() );
			assertEquals(
				new Amap<String, Alist<Expr>>().put( "out", new Alist<Expr>().add( new Str( "Hello Jorgen" ) ) ),
				rv.getRetMap() );
			assertEquals( new Alist<String>(), rv.getOut() );
			assertEquals( new Alist<Param>().add( new Param( new Name( "out", false ), false ) ), rv.getOutLst() );
		}

	}
	
	

}
