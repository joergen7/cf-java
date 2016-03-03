package de.huberlin.wbi.cfjava.asyntax;

public class Str extends ContentHolder implements Expr {
	
	public Str( final String content ) {
		super( content );
	}
	
	@Override
	public boolean isFinal() {
		return true;
	}
		
}
