package de.huberlin.wbi.cfjava.cuneiform;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;

public class HaltMsg {
	
	private final List<String> result;
	private final Integer line;
	private final String module;
	private final String reason;
	
	public HaltMsg( JSONArray r ) {
		
		int i;
		
		if( r == null )
			throw new IllegalArgumentException( "Result must not be null." );
		
		line = null;
		module = null;
		reason = null;
		
		result = new LinkedList<>();
		
		for( i = 0; i < r.length(); i++ )
			result.add( r.getString( i ) );
		
		
	}
	
	public HaltMsg( int line, String module, String reason ) {
		
		if( line <= 0 )
			throw new IllegalArgumentException( "Line number must be positive." );
		
		if( module == null )
			throw new IllegalArgumentException( "Module name must not be null." );
		
		if( reason == null )
			throw new IllegalArgumentException( "Reason must not be null." );
		
		result = null;
		
		this.line = line;
		this.module = module;
		this.reason = reason;
	}

	public Integer getLine() {
		return line;
	}
	
	public String getModule() {
		return module;
	}
	
	public String getReason() {
		return reason;
	}
	
	public boolean isOk() {
		return result != null;
	}
	
	public boolean isLevelWorkflow() {
		return module != null;
	}
	
	
}
