package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ParseTripleVisitorTest {

	@SuppressWarnings("static-method")
	@Test
	public void nilShouldBeRecognizedTest() throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "nil;";
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
		
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
		
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "bla blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) ).add( new Var( 1, "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "bla; blub;";
		
		query = new Alist<Expr>().add( new Var( 1, "blub" ) ).add( new Var( 1, "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "\"bla\";";
		
		query = new Alist<Expr>().add( new Str( "bla" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "5;";
		
		query = new Alist<Expr>().add( new Str( "5" ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Alist<Expr> xc, xt, xe;
		
		script = "if nil then \"bla\" else \"blub\" end;";
		
		xc = new Alist<>();
		xt = new Alist<Expr>().add( new Str( "bla" ) );
		xe = new Alist<Expr>().add( new Str( "blub" ) );
		
		query = new Alist<Expr>().add( new Cnd( 1, xc, xt, xe ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		script = "f();";
		
		var = new Var( 1, "f" );
		fa = new Amap<>();
		
		query = new Alist<Expr>().add( new App( 1, 1, var, fa ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		script = "f( x: x );";
		
		var = new Var( 1, "f" );
		fa = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 1, "x" ) ) );
		
		query = new Alist<Expr>().add( new App( 1, 1, var, fa ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Var var;
		Amap<String, Alist<Expr>> fa;
		
		script = "f( x: x, y: \"y\" );";
		
		var = new Var( 1, "f" );
		fa = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Var( 1, "x" ) ) )
			.put( "y", new Alist<Expr>().add( new Str( "y" ) ) );
		
		query = new Alist<Expr>().add( new App( 1, 1, var, fa ) );
		rho = new Amap<>();
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();
			
			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "x = \"x\";";
		
		query = new Alist<>();
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>().add( new Str( "x" ) ) );
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();

			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		ParseTriple ptExpect, ptActual;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		script = "x y = f();";
		
		query = new Alist<>();
		
		rho = new Amap<String, Alist<Expr>>()
			.put( "x", new Alist<Expr>()
				.add( new App( 1, 1, new Var( 1, "f" ),
					new Amap<String, Alist<Expr>>() ) ) )
			.put( "y", new Alist<Expr>()
					.add( new App( 1, 2, new Var( 1, "f" ),
							new Amap<String, Alist<Expr>>() ) ) );
		
		gamma = new Amap<>();
		
		ptExpect = new ParseTriple( query, rho, gamma );
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();

			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			ptActual = asv.visit( tree );
			
			assertEquals( ptExpect, ptActual );
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
		ParseTripleVisitor asv;
		String script;
		
		script = "x y = \"blub\";";
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new ParseTripleVisitor();
			
			tree = parser.script();

			assertEquals( 0, parser.getNumberOfSyntaxErrors() );

			asv.visit( tree );
		}
	}
}
