package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class ArgPair {
	
	private final Alist<InParam> inParamLst;
	private final Amap<String, Alist<Expr>> bindMap;

	public ArgPair( final Alist<InParam> inParamLst, final Amap<String, Alist<Expr>> bindMap ) {
		
		if( inParamLst == null )
			throw new IllegalArgumentException( "Input parameter list must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.inParamLst = inParamLst;
		this.bindMap = bindMap;
	}

	public Alist<InParam> getInParamLst() {
		return inParamLst;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

}
