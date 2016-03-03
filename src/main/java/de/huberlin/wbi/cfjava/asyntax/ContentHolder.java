package de.huberlin.wbi.cfjava.asyntax;

public abstract class ContentHolder {

	private final String content;

	public ContentHolder( final String content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Content string must not be null." );
		
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
