package de.huberlin.wbi.cfjava.parse;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = -7934572051518681928L;
	
	public ParseException( int line, String msg ) {
		super( "Line "+line+": "+msg );
	}

}
