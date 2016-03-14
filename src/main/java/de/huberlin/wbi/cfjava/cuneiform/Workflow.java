/* Cuneiform: A Functional Language for Large Scale Scientific Data Analysis
 *
 * Copyright 2016 Jörgen Brandt, Marc Bux, and Ulf Leser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.huberlin.wbi.cfjava.cuneiform;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import de.huberlin.wbi.cfjava.asyntax.Ctx;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.ResultKey;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.eval.EvalFn;
import de.huberlin.wbi.cfjava.eval.RequestCollector;
import de.huberlin.wbi.cfjava.parse.WorkflowListener;
import de.huberlin.wbi.cfjava.parse.CuneiformLexer;
import de.huberlin.wbi.cfjava.parse.CuneiformParser;
import de.huberlin.wbi.cfjava.pred.FinalAlistExprPred;

public class Workflow {

	private Ctx ctx;
	private Alist<Expr> query;
	
	public static Workflow createWorkflow( final String script ) {
		
		ANTLRInputStream input;
		CuneiformLexer lexer;
		CuneiformParser parser;
		CommonTokenStream tokenStream;
		ParseTree tree;
		int nerr;
		WorkflowListener asv;
		ParseTreeWalker walker;
		
		try( StringReader reader = new StringReader( script ) ) {
			
			input = new ANTLRInputStream( reader );
			lexer = new CuneiformLexer( input );
			tokenStream = new CommonTokenStream( lexer );
			parser = new CuneiformParser( tokenStream );
			asv = new WorkflowListener();
			walker = new ParseTreeWalker();
			
			tree = parser.script();

			nerr = parser.getNumberOfSyntaxErrors();
			if( nerr > 0 )
				throw new RuntimeException( "Encountered "+nerr+" syntax errors." );
			
			walker.walk( asv, tree );
			
			return new Workflow( asv.getQuery(), asv.getRho(), asv.getGamma() );			
		}
		catch( IOException e ) {
			throw new RuntimeException( e );
		}
	}
	
	public Workflow( Alist<Expr> query, Amap<String, Alist<Expr>> rho, Amap<String, Lam> gamma ) {
		
		if( query == null )
			throw new IllegalArgumentException( "Query must not be null." );
		
		if( rho == null )
			throw new IllegalArgumentException( "Rho must not be null." );
		
		if( gamma == null )
			throw new IllegalArgumentException( "Gamma must not be null." );
		
		this.query = query;
		this.ctx = new Ctx( rho, new RequestCollector(), gamma, new Amap<ResultKey, Alist<Expr>>() );
	}
	
	public boolean reduce() {
		
		EvalFn evalFn;
		FinalAlistExprPred finalPred;
		
		finalPred = new FinalAlistExprPred();
		evalFn = new EvalFn( ctx );
		
		query = evalFn.apply( query );
		
		return finalPred.test( query );
	}
	
	@Override
	public String toString() {		
		return new StringBuffer().append( '{' ).append( query ).append( ',' )
			.append( ctx ).append( '}' ).toString();
	}

	public Set<Request> getRequestSet() {		
		return ( ( RequestCollector )ctx.getMu() ).getRequestSet();
	}

	public void addReply( Reply reply ) {
		
		Amap<ResultKey, Alist<Expr>> omega;
		Amap<String, Alist<Expr>> retMap;
		int id;
		
		omega = ctx.getOmega();
		retMap = reply.getRetMap();
		id = reply.getId();
		for( String n : retMap.keys() )
			omega = omega.put( new ResultKey( n, id ), retMap.get( n ) );
		
		ctx = new Ctx( ctx.getRho(), ctx.getMu(), ctx.getGamma(), omega );
	}

	public Alist<Expr> getQuery() {
		return query;
	}

	public Amap<String, Alist<Expr>> getRho() {
		return ctx.getRho();
	}

	public Amap<String, Lam> getGamma() {
		return ctx.getGamma();
	}
}
