package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;

public class Cnd extends SrcLocated implements Expr {

	private Alist<Expr> condLst;
	private Alist<Expr> thenLst;
	private Alist<Expr> elseLst;
	
	public Cnd( int line, Alist<Expr> condLst, Alist<Expr> thenLst, Alist<Expr> elseLst ) {
		
		super( line );
		
		if( condLst == null )
			throw new IllegalArgumentException( "Condition expression list must not be null." );

		if( thenLst == null )
			throw new IllegalArgumentException( "Then expression list must not be null." );

		if( elseLst == null )
			throw new IllegalArgumentException( "Else expression list must not be null." );

		this.condLst = condLst;
		this.thenLst = thenLst;
		this.elseLst = elseLst;
	}

	public Alist<Expr> getCondLst() {
		return condLst;
	}

	public Alist<Expr> getThenLst() {
		return thenLst;
	}

	public Alist<Expr> getElseLst() {
		return elseLst;
	}

}
