package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;

public class Cnd extends SrcLocated implements Expr {

	private final Alist<Expr> condLst;
	private final Alist<Expr> thenLst;
	private final Alist<Expr> elseLst;
	
	public Cnd( final int line, final Alist<Expr> condLst, final Alist<Expr> thenLst, final Alist<Expr> elseLst ) {
		
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
