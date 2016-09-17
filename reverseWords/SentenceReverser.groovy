package reverseWords;

/*
 * Google Code Jam Problem: Reverse Words
 * https://code.google.com/codejam/contest/351101/dashboard#s=p1
 */

inputFilename = "input.txt"
r = new BufferedReader(new FileReader(inputFilename))

outputFilename = "output.txt"
fw = new FileWriter(outputFilename)

//The first line of the input contains the number of cases
printf("Number of entries is %d%n",Integer.parseInt(r.readLine()))

int i = 1
while(line = r.readLine()){
	//The expected line format are words seperated by whitespaces
	words = line.split(" ")
	reverseLine = new StringBuilder("")
	for (int l = words.length; l > 0; l--) {
		reverseLine.append(words[l-1] + " ")
	}
	fw.write("Case #" + i + ": " + reverseLine.toString() + "\n")
	i++
}

r.close()
fw.close()

printf("File %s successfully processed and results written to %s%n",
		inputFilename, outputFilename);