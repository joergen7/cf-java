package de.huberlin.wbi.cfjava.asyntax;

public class ResultKey extends IdHolder implements LabelHolder {

	private final String label;
	
	public ResultKey( final String label, final int id ) {
		
		super( id );
		
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
