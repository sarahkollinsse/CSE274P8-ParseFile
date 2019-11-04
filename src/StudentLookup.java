/**
 * Your implementation of the LookupInterface.  The only public methods
 * in this class should be the ones that implement the interface.  You
 * should write as many other private methods as needed.  Of course, you
 * should also have a public constructor.
 * 
 * @author // TODO: Add your name here
 */
  
 
public class StudentLookup implements LookupInterface {
	private Hash hash;
	public StudentLookup() {
		hash = new Hash();
	}

	@Override
	public void addString(int amount, String s) {
		hash.add(amount, s);
	}

	@Override
	public int lookupCount(String s) {
		return hash.getValue(s);
	}

	@Override
	public String lookupPopularity(int n) {
		return hash.popularity(n);
	}

	@Override
	public int numEntries() {
		return hash.getSize();
	}
    
}
