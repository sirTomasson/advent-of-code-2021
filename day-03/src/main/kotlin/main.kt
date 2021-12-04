import java.io.File
import kotlin.math.exp
import kotlin.math.pow

fun main() {
    println("Hello AoC day 03!!")



    val diagnostics = File("day-03/diagnostics.txt")

    val lines = diagnostics.readLines()
        .toList()

    val width = diagnostics.readLines().first().length
    val height = lines.size

    val matrix: Array<IntArray> = Array(width) { IntArray(height) }

    lines.forEachIndexed { i, line ->
        line.toCharArray().forEachIndexed { j, c ->
            val number = c.toString().toInt()
            matrix[i][j] = number
        }
    }



    val pivot: Array<IntArray> = Array(12) { IntArray(12) }




    val bits = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    lines.forEach {

        it.forEachIndexed { index, c ->
            bits[index] = bits[index] + c.toString().toInt()
        }
    }


    val gamma = bits.map {
        return@map if (it > lines.size / 2) {
            "1"
        } else {
            "0"
        }
    }.toList().joinToString("")

    val epsilon = gamma.map {
        return@map if (it == '0') {
            '1'
        } else {
            '0'
        }
    }.joinToString("")

    println("Gamma: $gamma => ${gamma.binaryToDecimal()} Epsilon: $epsilon => ${epsilon.binaryToDecimal()}.")
    println("Power consumption: ${gamma.binaryToDecimal().toInt()*epsilon.binaryToDecimal().toInt()}")

    // Find support rating by multiplying Oxygen generator rating
    // with CO2 scrubber rating

    //
}

fun String.binaryToDecimal(): Number {
    var decimal = 0f

    this.forEachIndexed { i, c ->
        val value = c.toString().toInt()
        val exp = (this.length - 1) - i
        decimal += value.times(2.toFloat().pow(exp))
    }
    return decimal
}


