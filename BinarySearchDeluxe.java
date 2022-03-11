package autocompleteproj;

import java.util.Comparator;

// Adapted from: https://www.geeksforgeeks.org/binary-search/?ref=lbp
public class BinarySearchDeluxe {
	
    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static int firstIndexOf(Term[] terms, Term searchPrefix, Comparator<Term> comparator) {
    	if ((terms == null) || (searchPrefix == null) || (comparator == null)) {throw new IllegalArgumentException();}
    	
    	int leftBound = 0;
    	int rightBound = terms.length - 1;
    	
    	while (leftBound <= rightBound) {
    		int middleIndex = leftBound + (rightBound - leftBound) / 2;
    		int compare = comparator.compare(searchPrefix, terms[middleIndex]);
    		
    		if (compare == 0) {
    			int tempIndex = middleIndex;
    			
    			while (true) {
    				if(tempIndex - 1 < 0)
    				{
    					return tempIndex;
    				}
    				if (comparator.compare(searchPrefix, terms[tempIndex - 1]) == 0) {
    					tempIndex--;
    				} else {
    					return tempIndex;
    				}
    			}
    		}
    		if (compare >= 1) {
				leftBound = middleIndex + 1;
			} else {
				rightBound = middleIndex - 1;
			}
    	}
    	return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static int lastIndexOf(Term[] terms, Term searchPrefix, Comparator<Term> comparator) {
    	if ((terms == null) || (searchPrefix == null) || (comparator == null)) {throw new IllegalArgumentException();}
    	//tempIndex + 1 > length
    	//[tempindex+1]
    	//tempindex++;
    	
    	int leftBound = 0;
    	int rightBound = terms.length - 1;
    	
    	while (leftBound <= rightBound) {
    		int middleIndex = leftBound + (rightBound - leftBound) / 2;
    		int compare = comparator.compare(searchPrefix, terms[middleIndex]);
    		
    		if (compare == 0) {
    			int tempIndex = middleIndex;
    			while (true) {
    				if(tempIndex + 1 >= terms.length)
    				{
    					return tempIndex;
    				}
    				if (comparator.compare(searchPrefix, terms[tempIndex + 1]) == 0) {
    					tempIndex++;
    				} else {
    					return tempIndex;
    				}
    			}
    		}
    		if (compare >= 1) {
				leftBound = middleIndex + 1;
			} else {
				rightBound = middleIndex - 1;
			}
    	}
    	return -1;
    	
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Term t0 = new Term("Apple", 0);
    	Term t1 = new Term("Art", 0);
    	Term t2 = new Term("Argument", 0);
    	Term t3 = new Term("Cart", 0);
    	Term t4 = new Term("Check", 0);
    	Term t5 = new Term("See", 0);
    	Term t6 = new Term("Seek", 0);
    	Term t7 = new Term("Secratary", 0);
    	Term t8 = new Term("Sleep", 0);
    	Term t9 = new Term("Walk", 0);
    	Term t10 = new Term("Zack", 0);
    	Term[] terms = {t0, t1, t2, t3, t4, t5, t6 ,t7, t8, t9, t10};
    	int first = firstIndexOf(terms, new Term("se", 0), Term.byPrefixOrder(2));
    	int last = lastIndexOf(terms, new Term("se", 0), Term.byPrefixOrder(2));
    	System.out.println("Frist Index:" + first);
    	System.out.println("Last Index:" + last);
    }
}
