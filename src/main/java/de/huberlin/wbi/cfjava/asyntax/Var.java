package de.huberlin.wbi.cfjava.asyntax;

public class Var extends SrcLocated implements LamSurrogate, Expr, LabelHolder {
	
	private final String label;

	public Var( final int line, final String label ) {
		
		super( line );
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}	
}
