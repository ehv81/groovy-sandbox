package simple

/* Project Euler Problem 1:
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

from = 0;
to = 1000;

printf("The sum of all integers divisible by 3 and 5" +
		" between %d and %d equals %d.%n",
		from,to,computeMultiplesOfThreeOrFive(from, to));

int computeMultiplesOfThreeOrFive(int from, int to) {
	sum = 0
	Stack divisibleIntegers = new Stack()

	for (int i = from; i < to; i++) {
		if (i % 3 == 0 || i % 5 == 0) { divisibleIntegers.push(i) }
	}

	while (divisibleIntegers) {
		sum += divisibleIntegers.pop()
	}

	sum
}
