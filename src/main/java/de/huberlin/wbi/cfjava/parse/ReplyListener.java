package de.huberlin.wbi.cfjava.parse;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Param;
import de.huberlin.wbi.cfjava.asyntax.Str;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ReplyListener extends EffiBaseListener {
	
	private int id;
	private Alist<String> binLst;
	private Alist<String> out;
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
		
		map = map.put( n, value );
	}

	@Override
	public void exitIdAssoc( EffiParser.IdAssocContext ctx ) {
		id = Integer.valueOf( ctx.INTLIT().getText() );
	}
	
	@Override
	public void exitTstartAssoc( EffiParser.TstartAssocContext ctx ) {
		tstart = Long.valueOf( ctx.INTLIT().getText() );
	}
	
	@Override
	public void exitTdurAssoc( EffiParser.TdurAssocContext ctx ) {
		tdur = Long.valueOf( ctx.INTLIT().getText() );
	}

	@Override
	public void exitOutAssoc( EffiParser.OutAssocContext ctx ) {
		out = binLst.reverse();
	}

	@Override
	public void exitRetAssoc( EffiParser.RetAssocContext ctx ) {
		retMap = map;
	}
	
	public int getId() {
		return id;
	}
	
	public long getTstart() {
		return tstart;
	}
	
	public long getTdur() {
		return tdur;
	}
	
	public Alist<String> getOut() {
		return out;
	}
	
	public Amap<String, Alist<Expr>> getRetMap() {
		return retMap;
	}

	public Alist<Param> getOutLst() {
		return null;
	}
}
