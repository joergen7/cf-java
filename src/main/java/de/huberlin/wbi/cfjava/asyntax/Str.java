package de.huberlin.wbi.cfjava.asyntax;

public class Str {
	
	private String content;

	public Str( String content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Content string must not be null." );
		
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

}
