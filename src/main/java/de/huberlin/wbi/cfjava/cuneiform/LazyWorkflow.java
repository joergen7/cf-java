package de.huberlin.wbi.cfjava.cuneiform;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class LazyWorkflow extends Workflow {
	
	private static final double ACTION_RATIO = 0.2;

	private int nFinishedReply;
	private int nPendingReply;
	private boolean lastRet;
	
	public LazyWorkflow( Alist<Expr> query, Amap<String, Alist<Expr>> rho, Amap<String, Lam> gamma ) {
		super( query, rho, gamma );
		
		nFinishedReply = 0;
		nPendingReply = 0;
		lastRet = false;
	}

	@Override
	public boolean reduce() {
		
		int nPendingFut, nRequest;
		
		nRequest = getRequestSet().size();
		
		if( nRequest == 0 ) {
			lastRet = super.reduce();
			return lastRet;
		}
		
		nPendingFut = nRequest-nFinishedReply;
		
		if( nPendingFut == 0 )
			return lastRet;
		
		if( nPendingReply/nPendingFut > ACTION_RATIO ) {
			lastRet = super.reduce();
			nFinishedReply += nPendingReply;
			nPendingReply = 0;
			
		}

		return lastRet;
	}
	
	@Override
	public void addReply( Reply reply ) {
		super.addReply( reply );
		nPendingReply++;
	}
}
