package de.huberlin.wbi.cfjava.parse;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Name;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ReplyListener extends EffiBaseListener {
	
	private Alist<String> binLst;
	private int id;
	private Alist<String> out;
	private Alist<Param> outLst;
	private Alist<Param> paramLst;
	private Amap<String, Alist<Expr>> retMap, map;
	private long tdur;
	private long tstart;
	private Alist<Expr> value;
	
	@Override
	public void enterBin( EffiParser.BinContext ctx ) {
		
		String s;
		
		s = ctx.STRLIT().getText();
		s = s.substring( 1, s.length()-1 );
		
		binLst = binLst.add( s );
	}
	
	@Override
	public void enterBinlst( EffiParser.BinlstContext ctx ) {
		binLst = new Alist<>();
	}

	@Override
	public void enterMap( EffiParser.MapContext ctx ) {
		map = new Amap<>();
	}

	
	@Override
	public void enterNeparamlst( EffiParser.NeparamlstContext ctx ) {
		paramLst = new Alist<>();
	}
	
	@Override public void enterStr( EffiParser.StrContext ctx ) {
		
		String s;
		
		s = ctx.STRLIT().getText();
		s = s.substring( 1, s.length()-1 );
		
		value = value.add( new Str( s ) );
	}
	
	@Override
	public void enterStrlst( EffiParser.StrlstContext ctx ) {
		value = new Alist<>();
	}

	@Override
	public void exitBinding( EffiParser.BindingContext ctx ) {
		
		String n;
		
		n = ctx.STRLIT().getText();
		n = n.substring( 1, n.length()-1 );
		
		map = map.put( n, value.reverse() );
	}

	
	@Override
	public void exitIdAssoc( EffiParser.IdAssocContext ctx ) {
		id = Integer.valueOf( ctx.INTLIT().getText() );
	}
	
	@Override
	public void exitNeparamlst( EffiParser.NeparamlstContext ctx ) {
		outLst = paramLst.reverse();
	}

	@Override
	public void exitOutAssoc( EffiParser.OutAssocContext ctx ) {
		out = binLst.reverse();
	}
	
	@Override
	public void exitParam( EffiParser.ParamContext ctx ) {
		
		Param param;
		String label;
		boolean isFile, isLst;
		
		label = ctx.STRLIT().getText();
		label = label.substring( 1, label.length()-1 );
		
		isFile = ctx.bool( 0 ).TRUE() != null;
		isLst = ctx.bool( 1 ).TRUE() != null;
			
		param = new Param( new Name( label, isFile ), isLst );
		
		paramLst = paramLst.add( param );
	}
	
	@Override
	public void exitRetAssoc( EffiParser.RetAssocContext ctx ) {
		retMap = map;
	}

	@Override
	public void exitTdurAssoc( EffiParser.TdurAssocContext ctx ) {
		tdur = Long.valueOf( ctx.INTLIT().getText() );
	}

	@Override
	public void exitTstartAssoc( EffiParser.TstartAssocContext ctx ) {
		tstart = Long.valueOf( ctx.INTLIT().getText() );
	}
	
	public int getId() {
		return id;
	}
	
	public Alist<String> getOut() {
		return out;
	}
	
	public Alist<Param> getOutLst() {
		return outLst;
	}
	
	public Amap<String, Alist<Expr>> getRetMap() {
		return retMap;
	}
	
	public long getTdur() {
		return tdur;
	}

	public long getTstart() {
		return tstart;
	}
}
