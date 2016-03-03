package de.huberlin.wbi.cfjava.asyntax;

public class Name implements LabelHolder {

	private final String label;
	private final boolean isFile;
	
	public Name( final String label, final boolean isFile ) {
		
		if( label == null )
			throw new IllegalArgumentException( "Label string must not be null." );
		
		if( label.isEmpty() )
			throw new IllegalArgumentException( "Label string must not be empty." );
		
		this.label = label;
		this.isFile = isFile;
	}

	public boolean isFile() {
		return isFile;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
