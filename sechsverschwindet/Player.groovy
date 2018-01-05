package sechsverschwindet

import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function

class Player {
	final String name
	final Function<Board, Boolean> strategy

	int numberOfSticks
	int numberOfRolls
	Map<Integer,AtomicInteger> rollResults = new HashMap<>()

	List<Integer> numberOfRollsHistory

	public Player(String name, Function<Board, Boolean> strategy) {
		this.name = name
		this.strategy = strategy
		numberOfRollsHistory = new ArrayList<>()
	}
	
	void initForNewGame(int numberOfSticks) {
		this.numberOfSticks = numberOfSticks
		numberOfRolls = 0
		rollResults = new HashMap<>()
	}

	/**
	 * @return true if player decides to quit his turn
	 */
	boolean takeTurn(Board board, Dice dice) {
		int result = dice.roll()
		numberOfRolls++
		rollResults.computeIfAbsent(Integer.valueOf(result), {n -> new AtomicInteger(0)}).incrementAndGet()

		boolean wasSuccessful = board.play(result)
		if (wasSuccessful) {
			numberOfSticks--
		} else {
			numberOfSticks++
		}

		wasSuccessful ? strategy.apply(board) : true
	}

	void printCurrentStatistics() {
		println "$name finished with $numberOfSticks sticks after $numberOfRolls rolls"
//		rollResults.entrySet().forEach { k -> println "Number of occurences of ${k.getKey()}: ${k.getValue()}" }
	}
	
	double computeAverageNumberOfRolls() {
		numberOfRollsHistory.stream().reduce({ a,b -> a + b }).orElse(Integer.valueOf(0)) / numberOfRollsHistory.size
	}
	
	void addResultToHistory() {
		numberOfRollsHistory.add(numberOfRolls)
	}

}
