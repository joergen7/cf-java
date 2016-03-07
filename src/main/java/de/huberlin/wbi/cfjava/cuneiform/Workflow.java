package de.huberlin.wbi.cfjava.cuneiform;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import de.huberlin.wbi.cfjava.asyntax.ParseTriple;
import de.huberlin.wbi.cfjava.eval.RequestCollector;
import de.huberlin.wbi.cfjava.parse.AsyntaxVisitor;
import de.huberlin.wbi.cfjava.parse.CuneiformLexer;
import de.huberlin.wbi.cfjava.parse.CuneiformParser;

public class Workflow {

	private final RequestCollector requestCollector;
	
	public Workflow( final String script ) throws IOException {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		AsyntaxVisitor asv;
		ParseTriple triple;
		
		requestCollector = new RequestCollector();

		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new AsyntaxVisitor();
			
			tree = parser.script();
			triple = asv.visit( tree );
			
			
		}
		
		
	}
}
