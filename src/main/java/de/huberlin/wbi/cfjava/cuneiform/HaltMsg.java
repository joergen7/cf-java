package de.huberlin.wbi.cfjava.cuneiform;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;

public class HaltMsg {
	
	private final List<String> result;
	
	public HaltMsg( JSONArray r ) {
		
		int i;
		
		if( r == null )
			throw new IllegalArgumentException( "Result must not be null." );
		
		result = new LinkedList<>();
		
		for( i = 0; i < r.length(); i++ )
			result.add( r.getString( i ) );
		

	}

	public boolean isOk() {
		return result != null;
	}
	
	
}
