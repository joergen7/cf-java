package de.huberlin.wbi.cfjava.asyntax;

public class ForBody extends ContentHolder implements Body {

	private final Lang lang;
	
	public ForBody( final Lang lang, final String content ) {
		
		super( content );
		
		if( lang == null )
			throw new IllegalArgumentException( "Language must not be null." );
		
		this.lang = lang;
	}

	public Lang getLang() {
		return lang;
	}
}
