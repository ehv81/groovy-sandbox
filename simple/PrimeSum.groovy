package simple
import java.util.Iterator
import java.util.LinkedList
import java.util.List

/*
 * Project Euler Problem 50:
 * 
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */

def primesBelow(long k) {
	if (k < 1) throw new IllegalArgumentException("parameter has to be a positive long");
	
	//2 and 3 are the only consecutive primes. This check in the beginning allows
	//to skip even numbers later on, which should result in a slight performance increase.
	if (k == 1) {return Collections.emptyList()}
	if (k == 2) {return Arrays.asList(2)}
	if (k == 3) {return Arrays.asList(2,3)}

	List<Long> primes = new LinkedList<Long>();
	primes.add(2L);
	primes.add(3L);
	long lastPrime = 3L;
	
	Iterator<Long> primeIterator = primes.iterator();
	long n = 5L;
	boolean nIsPrime;
	
	while (primes.size() < k) {
		nIsPrime = true;
		primeIterator = primes.iterator();

		while (primeIterator.hasNext() && nIsPrime == true) {
			if (n % primeIterator.next() == 0) {
				nIsPrime = false;
			}
		}

		if (nIsPrime) {
			lastPrime = n;
			primes.add(lastPrime);
		}
		//only consider odd numbers
		n += 2;
	}
//		System.out.println(primes.toString());
	return lastPrime;
}