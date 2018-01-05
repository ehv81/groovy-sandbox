package sechsverschwindet

import java.util.Map

class Board {
	final int numberOfSlots

	Map<Integer,Boolean> state
	int numberOfOccupiedSlots

	Board(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots
	}

	void init() {
		numberOfOccupiedSlots = 0

		state  = new HashMap<>()
		for (int i = 1; i < numberOfSlots; i++) {
			state.put(i, false)
		}
	}

	/**
	 * @return true if diceRollResult is a success, i.e., the corresponding slot is not occupied
	 */
	boolean play(int diceRollResult) {
		if (diceRollResult == numberOfSlots) {
			return true // winning slot is never occupied
		} else {
			boolean slotIsOccupied = state.get(diceRollResult)

			if (slotIsOccupied) {
				numberOfOccupiedSlots--
			} else {
				numberOfOccupiedSlots++
			}

			state.put(diceRollResult, !slotIsOccupied)

			!slotIsOccupied
		}
	}

	double fractionOfFreeSlots() {
		numberOfOccupiedSlots / numberOfSlots
	}
}