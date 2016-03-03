package de.huberlin.wbi.cfjava.asyntax;

public class Str implements Expr {
	
	private final String content;

	public Str( final String content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Content string must not be null." );
		
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
