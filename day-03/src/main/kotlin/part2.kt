import java.io.File

fun main(args: Array<String>) {
    println("Welcome to part 2")

    val diagnostics = File("day-03/diagnostics.txt")

    val lines = diagnostics.readLines()
        .toList()

    val width = diagnostics.readLines().first().length
    val height = lines.size

    val matrix: Array<IntArray> = Array(height) { IntArray(width) }

    lines.forEachIndexed { i, line ->
        line.toCharArray().forEachIndexed { j, c ->
            val number = c.toString().toInt()
            matrix[i][j] = number
        }
    }

    val oxygenRatingInBinary = getOxygenRatingInBinary(matrix)
    val oxygenRatingInDecimal = oxygenRatingInBinary.binaryToDecimal()
    println("Oxygen rating in binary: $oxygenRatingInBinary, and decimal $oxygenRatingInDecimal.")

    val cO2ScrubberRating = getCO2ScrubberRatingInBinary(matrix)
    val cO2ScrubberRatingInDecimal = cO2ScrubberRating.binaryToDecimal()
    println("CO2 Scrubber rating in binary: $cO2ScrubberRating, and decimal $cO2ScrubberRatingInDecimal.")

    val lifeSupportRating = oxygenRatingInDecimal.toInt() * cO2ScrubberRatingInDecimal.toInt()
    println("Life support rating = $lifeSupportRating.")
}

fun getOxygenRatingInBinary(matrix: Array<IntArray>): String {
    var filteredMatrix = matrix

    val width = matrix[0].size

    for (i in 0 until width) {
        if (filteredMatrix.size == 1) {
            break
        }
        val array = pivot(filteredMatrix)[i]
        val commonBit = mostCommonBit(array)
        filteredMatrix = filteredMatrix.filter {
            val bit = it[i]
            return@filter bit == commonBit
        }.toTypedArray()
    }

    return filteredMatrix[0].joinToString("")
}

fun getCO2ScrubberRatingInBinary(matrix: Array<IntArray>): String {
    var filteredMatrix = matrix

    val width = matrix[0].size

    for (i in 0 until width) {
        if (filteredMatrix.size == 1) {
            break
        }
        val array = pivot(filteredMatrix)[i]
        val commonBit = leastCommonBit(array)
        filteredMatrix = filteredMatrix.filter {
            val bit = it[i]
            return@filter bit == commonBit
        }.toTypedArray()
    }

    return filteredMatrix[0].joinToString("")
}

fun leastCommonBit(array: IntArray): Int {
    val sum = array.sum()
    val threshold = Math.ceil(array.size/2.0)
    return if (sum >= threshold) {
        0
    } else {
        1
    }
}

fun mostCommonBit(array: IntArray): Int {
    val sum = array.sum()
    val threshold = Math.ceil(array.size/2.0)
    return if (sum >= threshold) {
        1
    } else {
        0
    }
}

fun pivot(matrix: Array<IntArray>): Array<IntArray> {
    val width = matrix.size
    assert(matrix[0].isNotEmpty())
    val height = matrix[0].size
    val pivot: Array<IntArray> = Array(height) { IntArray(width) }

    matrix.forEachIndexed { i, ints ->
        ints.forEachIndexed { j, int ->
            pivot[j][i] = int
        }
    }
    return pivot
}

