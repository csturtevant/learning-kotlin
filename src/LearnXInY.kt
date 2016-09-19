/**
 * @author chrissturtevant0
 */

fun main(args: Array<String>) {
    val fooVal = 10
    var fooVar = 10
    fooVar = 20

    val foo : Int = 7 //Explicitly declaring the type

    val fooString = "My String Is Here!";
    val barString = "Printing on a new line?\nNo Problem!";
    val bazString = "Do you want to add a tab?\tNo Problem!";
    println(fooString);
    println(barString);
    println(bazString);

    val fooRawString = """
fun helloWorld(val name : String) {
   println("Hello, world!")
}
"""
    println(fooRawString)
    val fooTemplateString = "$fooString has ${fooString.length} characters"
    println(fooTemplateString)

    var fooNullable: String? = "abc"
    println(fooNullable?.length) // => 3
    println(fooNullable?.length ?: -1) // => 3
    fooNullable = null
    println(fooNullable?.length) // => null
    println(fooNullable?.length ?: -1) // => -1


    fun hello(name: String = "world") : String {
        return "Hello, $name!"
    }
    println(hello("foo")) // => Hello, foo!
    println(hello(name = "bar")) // => Hello, bar!
    println(hello()) // => Hello, world!

    fun odd(x: Int): Boolean = x % 2 == 1
    println(odd(6)) // => false
    println(odd(7)) // => true

    fun even(x: Int) = x % 2 == 0
    println(even(6)) // => true
    println(even(7)) // => false

    fun not(f: (Int) -> Boolean ) : (Int) -> Boolean {
        return {n -> !f.invoke(n)} //.invoke?
    }

    val notOdd = not(::odd)
    val notEven = not(::even)
    // Anonymous functions can be specified as arguments.
    val notZero = not {n -> n == 0}

    println(notOdd(6))

    val notPositive = not {it > 0}
    for (i in 0..4) {
        println("${notOdd(i)} ${notEven(i)} ${notZero(i)} ${notPositive(i)}")
    }

    class ExampleClass(val x: Int) {
        fun memberFunction(y: Int) : Int {
            return x + y
        }

        infix fun infixMemberFunction(y: Int) : Int {
            return x * y
        }
    }

    val fooExampleClass = ExampleClass(7)
    println(fooExampleClass.memberFunction(4))

    println(fooExampleClass infixMemberFunction 4)

    data class DataClassExample (val x: Int, val y: Int, val z: Int)
    val fooData = DataClassExample(1, 2, 4)
    println(fooData)
    val fooCopy = fooData.copy(y = 100)
    println(fooCopy)
    val (a, b, c) = fooCopy
    println("$a $b $c")

    data class MutableDataClassExample (var x: Int, var y: Int, var z: Int)
    val fooMutableDate = MutableDataClassExample(7, 4, 9)
    with (fooMutableDate) {
        x -= 2
        y += 2
        z--
    }
    println(fooMutableDate)

    val fooList = listOf("a", "b", "c")
    println(fooList.size) // => 3
    println(fooList.first()) // => a
    println(fooList.last()) // => c
    // Elements of a list can be accessed by their index.
    println(fooList[1]) // => ob


    val fooMutableList = mutableListOf("a", "b", "c")
    fooMutableList.add("d")
    println(fooMutableList.last()) // => d
    println(fooMutableList.size) // => 4

    val fooSet = setOf("a", "b", "c")
    println(fooSet.contains("a")) // => true
    println(fooSet.contains("z")) // => false

    val fooMap = mapOf("a" to 8, "b" to 7, "c" to 9)
    // Map values can be accessed by their key.
    println(fooMap["a"]) // => 8

    val fooSequence = generateSequence(1, {it + 1})
    val x = fooSequence.take(10).toList()
    println(x) // => [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    // An example of using a sequence to generate Fibonacci numbers:
    fun fibonacciSequence() : Sequence<Long> {
        var a = 0L
        var b = 1L

        fun next() : Long {
            val result = a + b
            a = b
            b = result
            return a
        }

        return generateSequence(::next)
    }
    val y = fibonacciSequence().take(10).toList()
    println(y) // => [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]

    val z = (1..9).map {it * 3}
            .filter {it < 20}
            .groupBy {it % 2 == 0}
            .mapKeys {if (it.key) "even" else "odd"}
    println(z) // => {odd=[3, 9, 15], even=[6, 12, 18]} //FIXME need to understand how this "even" works

    for (c in "hello") {
        println(c)
    }

    var ctr = 0
    while (ctr < 5) {
        println(ctr)
        ctr++
    }
    do {
        println(ctr)
        ctr++
    } while (ctr < 10)

    val num = 5
    val message = if (num % 2 == 0) "even" else "odd"
    println("$num is $message") // => 5 is odd

    val i = 10
    when {
        i < 7 -> println("first block")
        fooString.startsWith("hello") -> println("second block")
        else -> println("else block")
    }

    when (i) {
        0, 21 -> println("0 or 21")
        in 1..20 -> println("in the range 1 to 20")
        else -> println("none of the above")
    }

    var result = when (i) {
        0, 21 -> "0 or 21"
        in 1..20 -> "in the range 1 to 20"
        else -> "none of the above"
    }
    println(result)

    fun smartCastExample(x: Any) : Boolean {
        if (x is Boolean) {
            // x is automatically cast to Boolean
            return x
        } else if (x is Int) {
            // x is automatically cast to Int
            return x > 0
        } else if (x is String) {
            // x is automatically cast to String
            return x.isNotEmpty()
        } else {
            return false
        }
    }
    println(smartCastExample("Hello, world!")) // => true
    println(smartCastExample("")) // => false
    println(smartCastExample(5)) // => true
    println(smartCastExample(0)) // => false
    println(smartCastExample(true)) // => true

    fun String.remove(c: Char): String {
        return this.filter {it != c}
    }
    println("Hello, world!".remove('l')) // => Heo, word!




    println(EnumExample.A) // => A
    println(ObjectExample.hello()) // => hello

}
