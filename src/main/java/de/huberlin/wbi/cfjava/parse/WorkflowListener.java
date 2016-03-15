package de.huberlin.wbi.cfjava.parse;

import java.util.List;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Body;
import de.huberlin.wbi.cfjava.asyntax.Cnd;
import de.huberlin.wbi.cfjava.asyntax.Correl;
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

public class WorkflowListener extends CuneiformBaseListener {
	
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
	private static Alist<InParam> processInParamLst( List<CuneiformParser.InparamContext> ipcl ) {
		
		Alist<InParam> acc;
		
		acc = new Alist<>();
		
		for( CuneiformParser.InparamContext ipc : ipcl )			
			acc = acc.add( processInParam( ipc ) );
		
		return acc.reverse();
	}
	
	private static Lang processLang( CuneiformParser.LangContext lc ) {
		
		if( lc.BASH() != null )
			return Lang.BASH;
		
		if( lc.PYTHON() != null )
			return Lang.PYTHON;
		
		if( lc.R() != null )
			return Lang.R;
		
		throw new UnsupportedOperationException( "Language not supported: "+lc.getText() );
	}
	
	private static Param processParam( CuneiformParser.ParamContext pc ) {
		
		boolean isLst, isFile;
		String label;

		isLst = pc.LTAG() != null;
		isFile = pc.name().FILE() != null;
		label = pc.name().ID().getText();
		
		return new Param( new Name( label, isFile ), isLst );
	}
	
	private static Alist<Param> processParamLst( List<CuneiformParser.ParamContext> pcl ) {
		
		Alist<Param> acc;
		
		acc = new Alist<>();
		
		for( CuneiformParser.ParamContext pc : pcl )			
			acc = acc.add( processParam( pc ) );
		
		return acc.reverse();
	}

	private static Sign processSign( CuneiformParser.SignContext ctx ) {
		
		Alist<Param> lo;
		Alist<InParam> li;
		
		lo = processParamLst( ctx.param() );
		li = processInParamLst( ctx.inparam() );
		
		return new Sign( lo, li );
	}

	private Alist<Amap<String, Alist<Expr>>> bindStack;

	
	private Alist<Alist<Expr>> exprStack;

	private Amap<String, Lam> gamma;

	private Alist<Expr> query;

	@Override public void enterAppExpr( CuneiformParser.AppExprContext ctx ) {
		bindStack = bindStack.add( new Amap<String, Alist<Expr>>() );
	}

	
	@Override
	public void enterCompoundexpr( CuneiformParser.CompoundexprContext ctx ) {
		exprStack = exprStack.add( new Alist<Expr>() );
	}

	
	@Override
	public void enterDefun( CuneiformParser.DefunContext ctx ) {
		bindStack = bindStack.add( new Amap<String, Alist<Expr>>() );
	}
	
	@Override
	public void enterScript( CuneiformParser.ScriptContext ctx ) {
		
		exprStack = new Alist<>();
		bindStack = new Alist<Amap<String, Alist<Expr>>>().add( new Amap<String, Alist<Expr>>() );
		query = new Alist<>();
		gamma = new Amap<>();
	}


	
	@Override public void exitAppExpr( CuneiformParser.AppExprContext ctx ) {
		
		int line;
		App app;
		String lamName;
		
		line = ctx.app().ID().getSymbol().getLine();
		lamName = ctx.app().ID().getText();
		
		app = new App( line, 1, new Var( line, lamName ), bindStack.hd() );
		
		exprStack = exprStack.tl().add( exprStack.hd().add( app ) );
		bindStack = bindStack.tl();
	}

	@Override
	public void exitAssign( CuneiformParser.AssignContext ctx ) {
		
		int i, n;
		String label;
		Alist<Expr> x;
		SetChannelFn f;
		
		n = ctx.ID().size();
		for( i = 0; i < n; i++ ) {
			
			label = ctx.ID( i ).getText();
			f = new SetChannelFn( i+1 );
			
			try {
				x = exprStack.hd().map( f );
			}
			catch( @SuppressWarnings("unused") SetChannelException e ) {
				throw new ParseException( ctx.EQ().getSymbol().getLine(),
					"Cannot set channel of non-application expression." );
			}
			
			bindStack = bindStack.tl().add( bindStack.hd().put( label, x ) );
		}
		
		exprStack = exprStack.tl();
	}

	
	@Override public void exitBinding( CuneiformParser.BindingContext ctx ) {
		
		String n;
		
		n = ctx.ID().getText();
		bindStack = bindStack.tl().add( bindStack.hd().put( n, exprStack.hd() ) );
		exprStack = exprStack.tl();
	}

	
	@Override
	public void exitCndExpr( CuneiformParser.CndExprContext ctx ) {
		
		Alist<Expr> xc, xt, xe;
		int line;
		
		line = ctx.cnd().IF().getSymbol().getLine();

		xe = exprStack.hd().reverse();
		exprStack = exprStack.tl();
		xt = exprStack.hd().reverse();
		exprStack = exprStack.tl();
		xc = exprStack.hd().reverse();
		exprStack = exprStack.tl();
		
		exprStack = exprStack.tl().add( exprStack.hd().add( new Cnd( line, xc, xt, xe ) ) );
	}

	@Override
	public void exitDefun( CuneiformParser.DefunContext ctx ) {
		
		Sign sign;
		Body body;
		int line;
		String lamName;
		Lang lang;
		String script;
		
		line = ctx.DEFTASK().getSymbol().getLine();
		lamName = ctx.ID().getText();
		sign = processSign( ctx.sign() );
		
		if( ctx.lang() == null )
			body = new NatBody( bindStack.hd() );
		else {
			
			lang = processLang( ctx.lang() );
			script = ctx.BODY().getText();
			script = script.substring( 2, script.length()-2 );
			
			body = new ForBody( lang, script );
		}
		
		gamma = gamma.put( lamName, new Lam( line, lamName, sign, body ) );
		bindStack = bindStack.tl();
	}

	@Override
	public void exitIntLitExpr( CuneiformParser.IntLitExprContext ctx ) {
		exprStack = exprStack.tl().add( exprStack.hd().add( new Str( ctx.INTLIT().getText() ) ) );
	}
	

	@Override
	public void exitQuery( CuneiformParser.QueryContext ctx ) {
		query = query.append( exprStack.hd().reverse() );
		exprStack = exprStack.tl();
	}
	
	@Override
	public void exitStrLitExpr( CuneiformParser.StrLitExprContext ctx ) {
		
		String s;
		
		s = ctx.STRLIT().getText();
		s = s.substring( 1, s.length()-1 );
		
		exprStack = exprStack.tl().add( exprStack.hd().add( new Str( s ) ) );
	}
	
	@Override
	public void exitVarExpr( CuneiformParser.VarExprContext ctx ) {
		
		int line;
		String label;
		
		line = ctx.ID().getSymbol().getLine();
		label = ctx.ID().getText();
		
		exprStack = exprStack.tl().add( exprStack.hd().add( new Var( line, label ) ) );
	}

	public Amap<String, Lam> getGamma() {
		return gamma;
	}
	
	public Alist<Expr> getQuery() {
		return query;
	}
	
	public Amap<String, Alist<Expr>> getRho() {
		return bindStack.hd();
	}

}
