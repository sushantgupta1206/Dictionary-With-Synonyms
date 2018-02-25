import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

// using a trie data structure for implementation instead of two hash maps.
class TrieNode {

	// Constructing the type TrieNode
	private boolean isEnding;
	private Map<Character, TrieNode> children ;
	private Set<String> synSet; 
	private String def;
	
	// defining public setters and getters
	public boolean isEnding() {
		return isEnding;
	}

	public void setEnding(boolean isEnding) {
		this.isEnding = isEnding;
	}

	
	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(Character ch , TrieNode node) {
		this.children.put(ch, node);
	}

	public Set<String> getSynSet() {
		return synSet;
	}

	public void addSyn(String synonym){
		this.synSet.add(synonym);
	}
	
	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	// constructor to create a new HM and initialize to base values
	public TrieNode(){  
		children = new HashMap<Character, TrieNode>();
		isEnding = false;
		synSet = new HashSet<String>();
		def = null;
	}	
	
}
