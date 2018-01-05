package sechsverschwindet

class Dice {

	final int numberOfSides;

	Dice(int numberOfSides) {
		this.numberOfSides = numberOfSides
	}

	int roll() {
		(int) (Math.random() * numberOfSides) + 1
	}

}
