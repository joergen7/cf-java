package de.huberlin.wbi.cfjava.eval;

import java.util.function.Function;

import de.huberlin.wbi.cfjava.asyntax.App;
import de.huberlin.wbi.cfjava.asyntax.Fut;
import de.huberlin.wbi.cfjava.cuneiform.Request;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class RequestCollector implements Function<App, Fut> {
	
	private Amap<Request, Integer> ticketLst;

	@Override
	public Fut apply( App arg0 ) {
		return null;
	}

}
