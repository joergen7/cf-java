package de.huberlin.wbi.cfjava.parse;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
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
			ptActual = asv.visit( tree );
		
			assertEquals( ptExpect, ptActual );
		}
	}
}
