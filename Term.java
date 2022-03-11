package autocompleteproj;

import java.util.Arrays; 
import java.util.Comparator;

public class Term implements Comparable<Term> {

	public final String termValue;
	public final long termWeight;
	
	
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
    	if ((query == null) || (weight < 0)) {throw new IllegalArgumentException();}
    	
    	this.termValue = query;
    	this.termWeight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
    	return new ReverseWeightOrderComparator();
    }
    
   public static class ReverseWeightOrderComparator implements Comparator<Term> {

		@Override
		public int compare(Term t1, Term t2) {
			if (t1.termWeight > t2.termWeight) {
				return -1;
			} else if (t1.termWeight < t2.termWeight) {
				return 1;
			}
			return 0;
		}
    }   

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int charCount){
    	if (charCount < 0) {throw new IllegalArgumentException("Negative character count");}
    	return new PrefixOrderComparator(charCount);
    }
    
    public static class PrefixOrderComparator implements Comparator<Term> {

    	private final int charCount;
    	
    	public PrefixOrderComparator(int charCount) {
			this.charCount = charCount;
    	}
    	
		@Override
		public int compare(Term t1, Term t2) {
			String t1SubString = getTermSubString(t1);
			String t2SubString = getTermSubString(t2);
			
			return t1SubString.compareToIgnoreCase(t2SubString);
		}
		
		private String getTermSubString(Term t) {
			String termValue = t.termValue;
			int valueLength = termValue.length();
			if (valueLength < charCount) {
				return termValue.substring(0, valueLength);
			} else {
				return termValue.substring(0, charCount);
			}
		}
    	
    }
    
    

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
    	return this.termValue.compareToIgnoreCase(that.termValue);
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((termValue == null) ? 0 : termValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (termValue == null) {
			if (other.termValue != null)
				return false;
		} else if (!termValue.equals(other.termValue))
			return false;
		return true;
	}

	// Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
    	return termWeight + "\t" + termValue;
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Term term1 = new Term("Apple", 1);
    	Term term2 = new Term("Add", 60);
    	Term term3 = new Term("Aiden", 10000);
    	Term term4 = new Term("Aid", 100);
    	Term[] terms = {term1, term2,term3, term4};
    	System.out.println(Arrays.toString(terms));
    	Arrays.sort(terms, Term.byPrefixOrder(3));
    	System.out.println(Arrays.toString(terms));
    	
    	
    	
//    	Term t3 = new Term("Hey", 100);
//    	Term t1 = new Term("Hi", 5);
////    	Term t2 = new Term("Hello", 100);
//    	System.out.println(t1.toString());
//    	System.out.println(t2.toString());
//    	Term[] terms2 = {t1, t2, t3};
//    	System.out.println(Arrays.toString(terms2));
//    	Arrays.sort(terms2, byReverseWeightOrder());
//    	System.out.println(Arrays.toString(terms2));
//    	System.out.println(byReverseWeightOrder().compare(t1, t2));
    }
}
