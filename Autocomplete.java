package autocompleteproj;

import java.util.Arrays;

//The GUI stops suggesting after 2 how can this be fixed?
// Doesn't always suggest properly. 

public class Autocomplete {

	private Term[] terms;
	
    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
    	if (terms == null || checkForNull(terms)) {throw new IllegalArgumentException();}
//    	this.terms = new Term[terms.length];
    	this.terms = terms;
    	Arrays.sort(terms);
//    	System.out.println();
//    	System.out.println(Arrays.toString(terms));
    }
    
    private boolean checkForNull(Term[] terms) {
    	for (Term currentTerm : terms) {
    		if (currentTerm == null) {
    			return true;
    		}
    	}
    	return false;
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	if (prefix == null) {throw new IllegalArgumentException();}
    	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	if (firstIndex == -1 || lastIndex == -1) {return new Term[0];}
    	
    	Term[] suggestedMatches = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);
    	Arrays.sort(suggestedMatches, Term.byReverseWeightOrder());
    	return suggestedMatches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
    	if (prefix == null) {throw new IllegalArgumentException();}
    	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	return lastIndex - firstIndex + 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Term t1 = new Term("Zack", 6);
    	Term t2 = new Term("Apple", 60);
    	Term t3 = new Term("ape", 89);
    	Term t4 = new Term("read", 300);
    	Term t5 = new Term("Abs", 1000);
    	Term[] tArray = {t1, t2, t3, t4, t5};
//    	System.out.println(Arrays.toString(tArray));
    	Autocomplete ac = new Autocomplete(tArray);
    	System.out.println(Arrays.toString(ac.allMatches("rea")));
    }

}
