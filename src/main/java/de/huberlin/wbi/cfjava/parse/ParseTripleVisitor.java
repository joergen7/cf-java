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

package de.huberlin.wbi.cfjava.parse;

import java.util.List;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Correl;
import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.InParam;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.NatBody;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Sign;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.asyntax.Var;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.parse.CuneiformParser.BindingContext;

public class ParseTripleVisitor extends CuneiformBaseVisitor<ParseTriple> {
	
	@Override
	protected ParseTriple aggregateResult( ParseTriple a, ParseTriple b ) {
		
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		if( a == null ) {
			
			if( b == null )
				throw new UnsupportedOperationException( "Some argument must be non-null." );
			
			return b;
		}
		
		if( b == null )
			return a;
		
		query = a.getQuery().append( b.getQuery() );
		rho = a.getRho().merge( b.getRho() );
		gamma = a.getGamma().merge( b.getGamma() );
		
		return new ParseTriple( query, rho, gamma );
	}
	
	@Override
	public ParseTriple visitApp( CuneiformParser.AppContext ctx ) {
		
		Amap<String, Alist<Expr>> rho, fa;
		Amap<String, Lam> gamma;
		int line;
		Alist<Expr> x, value;
		Var var;
		String arg;
		
				
		rho = new Amap<>();
		gamma = new Amap<>();

		line = ctx.ID().getSymbol().getLine();
		var = new Var( line, ctx.ID().getText() );
		
		fa = new Amap<>();
		for( BindingContext bc : ctx.binding() ) {
			
			arg = bc.ID().getText();
			value = visit( bc.compoundexpr() ).getQuery();
			
			fa = fa.put( arg, value );
		}
		
		x = new Alist<Expr>().add( new App( line, 1, var, fa ) );
		
		return new ParseTriple( x, rho, gamma );
	}
	
	@Override
	public ParseTriple visitAssign( CuneiformParser.AssignContext ctx ) {
		
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Alist<Expr> query;
		
		gamma = new Amap<>();
		query = new Alist<>();
		rho = processAssign( ctx );
		
		return new ParseTriple( query, rho, gamma );
	}

	@Override
	public ParseTriple visitCnd( CuneiformParser.CndContext ctx ) {
		
		Alist<Expr> xc, xt, xe;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Alist<Expr> x;
		int line;
		
		rho = new Amap<>();
		gamma = new Amap<>();
	
		line = ctx.IF().getSymbol().getLine();
		xc = visit( ctx.compoundexpr( 0 ) ).getQuery();
		xt = visit( ctx.compoundexpr( 1 ) ).getQuery();
		xe = visit( ctx.compoundexpr( 2 ) ).getQuery();
		
		x = new Alist<Expr>().add( new Cnd( line, xc, xt, xe ) );
		
		return new ParseTriple( x, rho, gamma );
	}

	
	@Override
	public ParseTriple visitCompoundexpr( CuneiformParser.CompoundexprContext ctx ) {
		
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		if( ctx.NIL() != null )
			return new ParseTriple( new Alist<Expr>(), rho, gamma );
		
		return this.visitChildren( ctx );
	}
	
	@Override
	public ParseTriple visitExpr( CuneiformParser.ExprContext ctx ) {
		
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Alist<Expr> x;
		String label;
		int line;
		String content;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		if( ctx.ID() != null ) {
			
			label = ctx.ID().getText();
			line = ctx.ID().getSymbol().getLine();
			x = new Alist<Expr>().add( new Var( line, label ) );
			
			return new ParseTriple( x, rho, gamma );
		}
		
		if( ctx.STRLIT() != null ) {
			
			content = ctx.STRLIT().getText();
			content = content.substring( 1, content.length()-1 );
			x = new Alist<Expr>().add( new Str( content ) );
			
			return new ParseTriple( x, rho, gamma );
		}
		
		if( ctx.INTLIT() != null ) {

			content = ctx.INTLIT().getText();
			x = new Alist<Expr>().add( new Str( content ) );
			
			return new ParseTriple( x, rho, gamma );
		}
		
		return visitChildren( ctx );		
	}
	
	@Override
	public ParseTriple visitDefun( CuneiformParser.DefunContext ctx ) {
		
		String lamName;
		Sign sign;
		NatBody natBody;
		Lam lam;
		int line;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		Alist<Expr> query;
		
		lamName = ctx.ID().getText();
		sign = processSign( ctx.sign() );
		natBody = new NatBody( processAssignLst( ctx.assign() ) );
		line = ctx.ID().getSymbol().getLine();
		lam = new Lam( line, lamName, sign, natBody );
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<String, Lam>().put( lamName, lam );
		
		return new ParseTriple( query, rho, gamma );
	}

	private static Sign processSign( CuneiformParser.SignContext ctx ) {
		
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = processParamLst( ctx.param() );
		li = processInParamLst( ctx.inparam() );
		
		return new Sign( lo, li );
	}
	
	private static Alist<Param> processParamLst( List<CuneiformParser.ParamContext> pcl ) {
		
		Alist<Param> acc;
		
		acc = new Alist<>();
		
		for( CuneiformParser.ParamContext pc : pcl )			
			acc = acc.add( processParam( pc ) );
		
		return acc.reverse();
	}
	
	private static Param processParam( CuneiformParser.ParamContext pc ) {
		
		boolean isLst, isFile;
		String label;

		isLst = pc.LTAG() != null;
		isFile = pc.name().FILE() != null;
		label = pc.name().ID().getText();
		
		return new Param( new Name( label, isFile ), isLst );
	}
	
	private static Alist<InParam> processInParamLst( List<CuneiformParser.InparamContext> ipcl ) {
		
		Alist<InParam> acc;
		
		acc = new Alist<>();
		
		for( CuneiformParser.InparamContext ipc : ipcl )			
			acc = acc.add( processInParam( ipc ) );
		
		return acc.reverse();
	}
	
	private static InParam processInParam( CuneiformParser.InparamContext ipc ) {
		
		Alist<Name> acc;
		boolean isFile;
		String label;
		
		if( ipc.LSQUAREBR() != null ) {
			
			acc = new Alist<>();
			
			for( CuneiformParser.NameContext nc : ipc.name() ) {
				
				isFile = nc.FILE() != null;
				label = nc.ID().getText();
				
				acc = acc.add( new Name( label, isFile ) );
			}
			
			return new Correl( acc.reverse() );
		}
		
		return processParam( ipc.param() );
	}
	
	private Amap<String, Alist<Expr>> processAssignLst( List<CuneiformParser.AssignContext> acl ) {
		
		Amap<String, Alist<Expr>> acc;
		
		acc = new Amap<>();
		for( CuneiformParser.AssignContext ac : acl )
			acc = acc.merge( processAssign( ac ) );
		
		return acc;
	}
	
	private Amap<String, Alist<Expr>> processAssign( CuneiformParser.AssignContext ctx ) {
		
		Amap<String, Alist<Expr>> rho;
		String varname;
		Alist<Expr> rhs, rhs1;
		int i, n;
		
		rho = new Amap<>();

		rhs = visit( ctx.compoundexpr() ).getQuery();
		
		n = ctx.ID().size();
		for( i = 0; i < n; i++ ) {
			
			varname = ctx.ID( i ).getText();
			
			try {
				rhs1 = rhs.map( new SetChannelFn( i+1 ) );
			}
			catch( @SuppressWarnings("unused") SetChannelException pe ) {
				throw new ParseException(
					ctx.EQ().getSymbol().getLine(),
					"Unable to set channel in right hand side of assignment." );
			}
			
			rho = rho.put( varname, rhs1 );
		}	
		
		return rho;
	}	
}
