package de.huberlin.wbi.cfjava.cuneiform;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.asyntax.Expr;
import de.huberlin.wbi.cfjava.asyntax.Lam;
import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class Request {
	
	private final Lam lam;
	private final Amap<String, Alist<Expr>> bindMap;

	public Request( final Lam lam, final Amap<String, Alist<Expr>> bindMap ) {
		
		if( lam == null )
			throw new IllegalArgumentException( "Lambda must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lam = lam;
		this.bindMap = bindMap;
	}

	public Lam getLam() {
		return lam;
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 733, 101 )
			.append( lam )
			.append( bindMap ).toHashCode();
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		Request rhs;
		
		if( !( obj instanceof Request ) )
			return false;

		if( obj == this )
			return true;
		
		rhs = ( Request )obj;
		
		return new EqualsBuilder()
			.append( lam, rhs.lam )
			.append( bindMap, rhs.bindMap ).isEquals();
	}
}
