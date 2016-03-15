package de.huberlin.wbi.cfjava.cuneiform;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.IdHolder;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.parse.EffiLexer;
import de.huberlin.wbi.cfjava.parse.EffiParser;
import de.huberlin.wbi.cfjava.parse.ReplyListener;

public class Reply extends IdHolder {
	
	private final Amap<String, Alist<Expr>> retMap;
	private final Alist<String> out;
	private final long tstart;
	private final long tdur;
	private final Alist<Param> outLst;
	
	public Reply( int id, Alist<Param> lo, Amap<String, Alist<Expr>> retMap, Alist<String> out,
		long tstart, long tdur) {
		
		super( id );
		
		if( lo == null )
			throw new IllegalArgumentException( "Output parameter list must not be null." );
		
		if( lo.isEmpty() )
			throw new IllegalArgumentException( "Output parameter list must not be empty." );
		
		if( out == null )
			throw new IllegalArgumentException( "Output string list must not be null." );
		
		if( retMap == null )
			throw new IllegalArgumentException( "Return map must not be null." );
		
		if( tstart < 0 )
			throw new IllegalArgumentException( "Start time must not be negative." );
		
		if( tdur < 0 )
			throw new IllegalArgumentException( "Duration time must not be negative." );
		
		this.retMap = retMap;
		this.out = out;
		this.tstart = tstart;
		this.tdur = tdur;
		this.outLst = lo;
	}

	public static Reply createReply( String summary ) {
		
		ANTLRInputStream input;
		EffiLexer lexer;
		CommonTokenStream tokenStream;
		EffiParser parser;
		ReplyListener rv;
		ParseTree tree;
		ParseTreeWalker walker;
		int nerr;

		try( StringReader reader = new StringReader( summary ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new EffiLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new EffiParser( tokenStream );
			rv = new ReplyListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();
			
			nerr = parser.getNumberOfSyntaxErrors();
			if( nerr > 0 )
				throw new RuntimeException( "Encountered "+nerr+" syntax errors." );

			walker.walk( rv, tree );

			return new Reply( rv.getId(), rv.getOutLst(), rv.getRetMap(), rv.getOut(),
				rv.getTstart(), rv.getTdur() );
		}
		catch( IOException e ) {
			throw new RuntimeException( e );
		}
	}

	public Amap<String, Alist<Expr>> getRetMap() {
		return retMap;
	}

	public String getStdout() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		for( String s : out )
			buf.append( s ).append( '\n' );
		
		return buf.toString();
	}

	public long getTstart() {
		return tstart;
	}

	public long getTdur() {
		return tdur;
	}
	
	public List<String> getStageOutFilenameList() {
		
		List<String> l;
		
		l = new ArrayList<>();
		
		for( Param param : outLst )	
			if( param.getName().isFile() )
				for( Expr expr : retMap.get( param.getName().getLabel() ) )
					l.add( ( ( Str )expr ).getContent() );
		
		return l;
	}

	public Alist<Param> getOutLst() {
		return outLst;
	}

}
