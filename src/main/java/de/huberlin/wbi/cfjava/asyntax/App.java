package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.huberlin.wbi.cfjava.data.Alist;
import de.huberlin.wbi.cfjava.data.Amap;

public class App extends MultiValue implements Expr {
	
	private final Amap<String, Alist<Expr>> bindMap;
	private final LamSurrogate lamSurrogate;
	
	public App( final int line, final int channel, final LamSurrogate lamSurrogate, final Amap<String, Alist<Expr>> bindMap ) {
		
		super( line, channel );

		if( lamSurrogate == null )
			throw new IllegalArgumentException( "Lambda surrogate must not be null." );
		
		if( bindMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lamSurrogate = lamSurrogate;
		this.bindMap = bindMap;
	}

	@Override
	public boolean equals( Object obj ) {
		
		App rhs;
		
		if( !( obj instanceof App ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( App )obj;
		
		return new EqualsBuilder()
			.appendSuper( super.equals( rhs ) )
			.append( bindMap, rhs.bindMap )
			.append( lamSurrogate, rhs.lamSurrogate ).isEquals();
	}

	public Amap<String, Alist<Expr>> getBindMap() {
		return bindMap;
	}
	
	public LamSurrogate getLamSurrogate() {
		return lamSurrogate;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.appendSuper( super.hashCode() )
			.append( bindMap )
			.append( lamSurrogate ).toHashCode();
	}
}
