package de.huberlin.wbi.cfjava.asyntax;

import de.huberlin.wbi.cfjava.data.Alist;

public class Sign {

	private final Alist<Param> outLst;
	private final Alist<InParam> inLst;
	
	public Sign( final Alist<Param> outLst, final Alist<InParam> inLst ) {
		
		if( outLst == null )
			throw new IllegalArgumentException( "Output parameter list must not be null." );
		
		if( outLst.isEmpty() )
			throw new IllegalArgumentException( "Output parameter list must not be empty." );
		
		if( inLst == null )
			throw new IllegalArgumentException( "Input parameter list must not be null." );
		
		this.outLst = outLst;
		this.inLst = inLst;
	}

	public Alist<Param> getOutLst() {
		return outLst;
	}

	public Alist<InParam> getInLst() {
		return inLst;
	}

}
