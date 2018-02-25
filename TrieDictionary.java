import java.io.*;
//import java.util.*;

public class TrieDictionary {

	// Private so that it can be only accessed with public functions
	private final TrieNode root; 

	//create the instance of the TrieNode
	public TrieDictionary(){
		root = new TrieNode();
	}

	// inserting a new word with definition
	public void insert(String word, String def) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			// children is a hashMap of it child node characters
			TrieNode node = current.getChildren().get(ch);
			if (node == null) {
				node = new TrieNode();
				current.setChildren(ch, node);
			}
			current = node;
		}
		//set end word flag = true and set the definition as specified
		current.setEnding(true);
		current.setDef(def);

	}

	// adding synonyms to existing words

	public void addSynonym(String word, String synonym){

		TrieNode node = search(word);
		if(node == null) System.out.println("Word doesnt exist for the synonym");  // sent you an email you for this doubt about invalid inputs 
		else{
			node.addSyn(synonym);
		}
	}


	// search for presence of a word (using node return type to avoid duplicate for other situations)
	public TrieNode search(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			//if node does not exist for given char then return false
			if (node == null) {
				return null;
			}
			current = node;
		}
		//return node if current's isEnding is true else return false.
		return current.isEnding() ? current : null;
	}

	// get word and word definition
	public void giveDefinition(String word) {
		TrieNode node = search(word);
		if(node == null) {
			System.out.println("Word not found!");
		} else {
			//node.getDef();
			System.out.println(word + ":" + node.getDef());
		}

	}

	// get word and its synonyms 
	public void giveSynonyms(String word) {
		TrieNode node = search(word);
		if(node == null) {
			System.out.println("Word not found!");
		} else {

			System.out.println(word + ":" + String.join(",", node.getSynSet()));
		}

	}


	public static void main(String[] args) throws Exception{

		// if no arguments passed (edge case)
		if(args.length == 0) {
			System.out.println("Filename not entered. Please re-run");
			return;
		}

		// Create object for the driving class
		TrieDictionary dictionary = new TrieDictionary();

		// The path of the file to open is taken as args[0].
		String fileName = args[0];

		// To read each line of the input
		String input = null;

		try {

			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((input = bufferedReader.readLine()) != null) {
				String[] inputs = input.split(":");  // splitting the input with the :
				if(inputs.length == 0) {
					System.out.println("Please enter something to process"); // in case of invalid inputs
					continue;
				}
				switch(inputs[0]){
				
				// case to add words and definition
				case "addWord" : {
					String word = inputs.length > 1 ? inputs[1]: "";
					String definition = inputs.length > 1 ? inputs[2]: "";
					if(word.equals("")) System.out.println("Can't enter blank word.");  // in case of invalid inputs
					else {
						dictionary.insert(word, definition); // using object to call function
						//System.out.println("Success");
					}
					break;
				}

				// case to add synonyms
				case "addSynonym" : {

					String word = inputs.length > 1 ? inputs[1]: "";
					String synonym = inputs.length > 1 ? inputs[2]: "";
					if(word.equals("") || synonym.equals("")) System.out.println("Can't enter blank word or blank synonym.");
					else {
						TrieNode synonymWord = dictionary.search(synonym); 
						// if synonym is not present in the dictionary 
						if(synonymWord != null){
							// add synonym adds to words if it exists
							dictionary.addSynonym(word, synonym);
							dictionary.addSynonym(synonym, word);
						}else{
							
							TrieNode wordNode = dictionary.search(word);
							// add to existing set or list
							dictionary.insert(synonym, wordNode.getDef());
							for(String syn : wordNode.getSynSet()){
								// linking all synonym list elements with the new synonym
								dictionary.addSynonym(syn, synonym);
								dictionary.addSynonym(synonym, syn);
							}
							// now linking the word to syn and syn to word
							dictionary.addSynonym(word, synonym);
							dictionary.addSynonym(synonym, word);
						}
					}
					break;
				}
				// case to find the word and dictionary
				case "lookupWord" : {
					String word = inputs.length > 1 ? inputs[1]: "";
					if(word.equals("")) System.out.println("Can't search blank words");  // invalid input
					dictionary.giveDefinition(word);
					break;
				}
				
				// case to find synonyms
				case "lookupSynonyms" : {
					String word = inputs.length > 1 ? inputs[1]: "";
					if(word.equals("")) System.out.println("Can't search blank words");
					dictionary.giveSynonyms(word);
					break;
				}
				// default case to check if functions are entered correctly
				default : {
					System.out.println("Invalid operation");
				}
			}

			}

			// close the file read
			bufferedReader.close();         
		}
		catch(FileNotFoundException e) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException e) {
			System.out.println("Error reading file '" + fileName + "'");                  
		}
	}
}		
