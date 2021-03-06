package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.ForBody;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Lang;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.NatBody;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class WorkflowListenerTest {

	@SuppressWarnings("static-method")
	@Test
	public void nilShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "nil;";
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}

	@SuppressWarnings("static-method")
	@Test
	public void varShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void multiElementCompoundExprShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "bla blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) ).add( new Var( 1, "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void multipleQueriesShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "bla; blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) ).add( new Var( 1, "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void strLitShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "\"bla\";";
		
		query = new Alist<Expr>().add( new Str( "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	public void intLitShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "5;";
		
		query = new Alist<Expr>().add( new Str( "5" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void cndShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		Alist<Expr> xc, xt, xe;
		
		script = "if nil then \"bla\" else \"blub\" end;";
		
		xc = new Alist<>();
		xt = new Alist<Expr>().add( new Str( "bla" ) );
		xe = new Alist<Expr>().add( new Str( "blub" ) );
		
		query = new Alist<Expr>().add( new Cnd( 1, xc, xt, xe ) );
		rho = new Amap<>();
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "f();";
		
		
		
		query = new Alist<Expr>().add( new App( 1, 1, new Var( 1, "f" ), new Amap<String, Alist<Expr>>() ) );
		rho = new Amap<>();
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appWithVarArgShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "f( x: x );";
		
		
		
		query = new Alist<Expr>().add( new App( 1, 1, new Var( 1, "f" ), new Amap<String, Alist<Expr>>().put( "x", new Alist<Expr>().add( new Var( 1, "x" ) ) ) ) );
		rho = new Amap<>();
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void appWithVarAndStrArgShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "f( x: x, y: \"y\" );";
		
		query = new Alist<Expr>()
			.add( new App(
				1,
				1,
				new Var( 1, "f" ),
				new Amap<String, Alist<Expr>>()
					.put( "x", new Alist<Expr>().add( new Var( 1, "x" ) ) )
					.put( "y", new Alist<Expr>().add( new Str( "y" ) ) ) ) );
					
		rho = new Amap<>();
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void assignShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "x = \"x\";";
		
		query = new Alist<>();
		rho = new Amap<String, Alist<Expr>>().put( "x", new Alist<Expr>().add( new Str( "x" ) ) );
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void assignMultipleValuesShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "x y = f();";
		
		query = new Alist<>();
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new App( 1, 1, new Var( 1, "f" ), new Amap<String, Alist<Expr>>() ) ) )
			.put( "y", new Alist<Expr>().add( new App( 1, 2, new Var( 1, "f" ), new Amap<String, Alist<Expr>>() ) ) );
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test( expected=ParseException.class )
	public void assignMultipleValuesToStrShouldThrowPeTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		
		script = "x y = \"blub\";";
		
		query = new Alist<>();
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new App( 1, 1, new Var( 1, "f" ), new Amap<String, Alist<Expr>>() ) ) )
			.put( "y", new Alist<Expr>().add( new App( 1, 2, new Var( 1, "f" ), new Amap<String, Alist<Expr>>() ) ) );
		gamma = new Amap<>();


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void nativeDeftaskShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		Lam lam;
		Sign sign;
		NatBody body;
		
		script = "deftask f( out : ) { out = \"A\"; }";
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>() );
		body = new NatBody(
			new Amap<String, Alist<Expr>>()
				.put( "out", new Alist<Expr>().add( new Str( "A" ) ) ) );
		lam = new Lam( 1, "f", sign, body );
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<String, Lam>().put( "f", lam );


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}

	@SuppressWarnings("static-method")
	@Test
	public void foreignDeftaskShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		WorkflowListener asv;
		String script;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		ParseTreeWalker walker;
		Lam lam;
		Sign sign;
		ForBody body;
		
		script = "deftask f( out : )in bash *{out=\"A\"}*";
		
		sign = new Sign(
			new Alist<Param>().add( new Param( new Name( "out", false ), false ) ),
			new Alist<InParam>() );
		body = new ForBody( Lang.BASH, "out=\"A\"" );
		lam = new Lam( 1, "f", sign, body );
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<String, Lam>().put( "f", lam );


		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );
			
			walker.walk( asv, tree );
			
			assertEquals( query, asv.getQuery() );
			assertEquals( rho, asv.getRho() );
			assertEquals( gamma, asv.getGamma() );
		}
	}
}


