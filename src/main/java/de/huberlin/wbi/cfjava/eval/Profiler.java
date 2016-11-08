package de.huberlin.wbi.cfjava.eval;

public interface Profiler {
	public void reportTime( String tag, Long startTime, Long timeMs );	
}
