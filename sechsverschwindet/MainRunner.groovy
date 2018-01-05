package sechsverschwindet

import java.util.concurrent.atomic.AtomicInteger

int numberOfSticksPerPlayer = 20
int numberOfDiceSides = 6
int numberOfGames = 100
boolean printStatisticsPerGame = false

Board board = new Board(numberOfDiceSides)
Dice dice = new Dice(numberOfDiceSides)

def players = [
	new Player("'Greedy strategy'", { b -> false }),
	new Player("'Threshold > 0.2 strategy'", { b -> b.fractionOfFreeSlots() > 0.3 }),
	new Player("'Threshold > 0.5 strategy'", { b -> b.fractionOfFreeSlots() > 0.5 }),
	new Player("'Threshold > 0.8 strategy'", { b -> b.fractionOfFreeSlots() > 0.8 }),
]

Map<Player, AtomicInteger> numberOfWins = new HashMap<>()

for (int i = 1; i <= numberOfGames; i++) {
	board.init()
	players.each { it.initForNewGame(numberOfSticksPerPlayer) }

	Player activePlayer = players[ i % players.size ] // alternating starting players

	boolean gameOver = false
	while (!gameOver) {
		boolean switchPlayer = activePlayer.takeTurn(board, dice)
		if (activePlayer.numberOfSticks == 0) {
			numberOfWins.computeIfAbsent(activePlayer, {p -> new AtomicInteger(0)}).incrementAndGet()
			gameOver = true
		} else if (switchPlayer) {
			int nextPlayerIndex = (++ players.findIndexOf { it.is activePlayer }) % players.size()
			activePlayer = players[nextPlayerIndex]
		} 
	}

	players.each { it.addResultToHistory() }

	if (printStatisticsPerGame) {
		println "\n-------------\n"
		println "Statistics of game # $i with $numberOfDiceSides-sided dice and $numberOfSticksPerPlayer sticks per player:\n"
		players.each { it.printCurrentStatistics() }
		println "\nWinner: $activePlayer.name"
	}
}

println "\n------------- Final statistics -------------\n"

println "Result of $numberOfGames played games with $numberOfDiceSides-sided dice and $numberOfSticksPerPlayer sticks per player:\n"
players.each {
	println "$it.name: number of wins = ${numberOfWins.get(it)}, average number of rolls = ${it.computeAverageNumberOfRolls()}"	
}
String nameOfOverallWinningPlayer = numberOfWins.entrySet().stream().reduce({ a,b -> (a.getValue().intValue() > b.getValue().intValue() ? a : b) }).map({ e -> e.getKey().name }).get()
println "\nOverall winner: $nameOfOverallWinningPlayer"
