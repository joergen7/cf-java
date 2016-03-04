package de.huberlin.wbi.cfjava.asyntax;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 701 )
			.append( outLst )
			.append( inLst ).toHashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		
		Sign rhs;
		
		if( !( obj instanceof Sign ) )
			return false;
		
		if( obj == this )
			return true;
		
		rhs = ( Sign )obj;
		
		return new EqualsBuilder()
			.append( outLst, rhs.outLst )
			.append( inLst, rhs.inLst ).isEquals();
	}
}
