package de.huberlin.wbi.cfjava.cuneiform;

import static org.junit.Assert.*;

import org.junit.Test;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;
import de.huberlin.wbi.cfjava.eval.DefaultProfiler;

public class WorkflowTest {

	@SuppressWarnings("static-method")
	@Test
	public void constructorArgsShouldBeRetrievable() {
		
		Workflow workflow;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		query = new Alist<>();
		rho = new Amap<>();
		gamma = new Amap<>();
		
		workflow = new Workflow( query, rho, gamma, new DefaultProfiler() );
		
		assertSame( query, workflow.getQuery() );
		assertSame( rho, workflow.getRho() );
		assertSame( gamma, workflow.getGamma() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullQueryTest() {
		
		Workflow workflow;
		Amap<String, Alist<Expr>> rho;
		Amap<String, Lam> gamma;
		
		rho = new Amap<>();
		gamma = new Amap<>();
		
		workflow = new Workflow( null, rho, gamma, new DefaultProfiler() );		
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullRhoTest() {
		
		Workflow workflow;
		Alist<Expr> query;
		Amap<String, Lam> gamma;
		
		query = new Alist<>();
		gamma = new Amap<>();
		
		workflow = new Workflow( query, null, gamma, new DefaultProfiler() );
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test( expected=IllegalArgumentException.class )
	public void constructorShouldThrowIaeOnNullGammaTest() {
		
		Workflow workflow;
		Alist<Expr> query;
		Amap<String, Alist<Expr>> rho;
		
		query = new Alist<>();
		rho = new Amap<>();
		
		workflow = new Workflow( query, rho, null, new DefaultProfiler() );
	}
}
