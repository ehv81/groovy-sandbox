package simple

keyObject = new Integer(0)
valueObject = new Integer(42)

m = [ keystring : valueObject, (keyObject) : valueObject ]

p = { println it }
def printentry = { entry -> p entry.key ; p entry.value }

m.each { printentry it }