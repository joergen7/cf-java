package de.huberlin.wbi.cfjava.asyntax;

public class Lam extends SrcLocated implements LamSurrogate, LamNameHolder {

	private final String lamName;
	private final Sign sign;
	private final Body body;
	
	public Lam( final int line, final String lamName, final Sign sign, final Body body ) {
		
		super( line );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( sign == null )
			throw new IllegalArgumentException( "Signature must not be null." );
		
		if( body == null )
			throw new IllegalArgumentException( "Body must not be null." );
		
		this.lamName = lamName;
		this.sign = sign;
		this.body = body;
	}

	@Override
	public String getLamName() {
		return lamName;
	}

	public Sign getSign() {
		return sign;
	}

	public Body getBody() {
		return body;
	}

}
