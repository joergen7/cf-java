package de.huberlin.wbi.cfjava.cuneiform;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;

public class HaltMsg {
	
	private final List<String> result;
	private final Integer line;
	private final String module;
	private final String reason;
	private final String id;
	private final Integer appLine;
	private final String lamName;
	private final String script;
	private final String output;
	
	public HaltMsg( JSONArray r ) {
		
		int i;
		
		if( r == null )
			throw new IllegalArgumentException( "Result must not be null." );
		
		line = null;
		module = null;
		reason = null;
		id = null;
		appLine = null;
		lamName = null;
		script = null;
		output = null;
		
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
		id = null;
		appLine = null;
		lamName = null;
		script = null;
		output = null;
		
		this.line = line;
		this.module = module;
		this.reason = reason;
	}
	
	public HaltMsg( String id, int appLine, String lamName, String script, String output ) {
		
		if( id == null )
			throw new IllegalArgumentException( "Id must not be null." );
		
		if( id.isEmpty() )
			throw new IllegalArgumentException( "Id must not be empty." );
		
		if( appLine <= 0 )
			throw new IllegalArgumentException( "Application line must be positive." );
		
		if( lamName == null )
			throw new IllegalArgumentException( "Lambda name must not be null." );
		
		if( lamName.isEmpty() )
			throw new IllegalArgumentException( "Lambda name must not be empty." );
		
		if( script == null )
			throw new IllegalArgumentException( "Script must not be null." );
		
		if( output == null )
			throw new IllegalArgumentException( "Output must not be null." );
		
		result = null;
		line = null;
		module = null;
		reason = null;
		
		this.id = id;
		this.appLine = appLine;
		this.lamName = lamName;
		this.script = script;
		this.output = output;
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
	
	public String getId() {
		return id;
	}
	
	public Integer getAppLine() {
		return appLine;
	}
	
	public String getLamName() {
		return lamName;
	}
	
	public String getScript() {
		return script;
	}
	
	public String getOutput() {
		return output;
	}
	
	public boolean isOk() {
		return result != null;
	}
	
	public boolean isErrorWorkflow() {
		return module != null;
	}
	

	public boolean isErrorTask() {
		return id != null;
	}
}
