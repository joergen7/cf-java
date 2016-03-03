package de.huberlin.wbi.cfjava.asyntax;

public interface Expr {
	default public boolean isFinal() { return false; } 
}
