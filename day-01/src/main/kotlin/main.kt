import java.io.File

fun main() {
    println("Hello AoC 2021!")

    // Solution to part (1)
    val measurements = File("day-01/depth-measurements.txt")
        .readLines().map { it.toInt() }

    var previous = 0
    var count = 0

    measurements.forEach {
        if (previous != 0 && it > previous) {
            count++
        }
        previous = it
    }

    // Print answer to part (1)
    println("Answer to Part 01! The count is: ${count}.")

    // Solution to part (2)
    // 1* Create sums of sliding window
    // 2* Iterate the sums of sliding windows
    // and compare previous with current measurements

    var i = 0
    var prevWindow = 0
    // reset count
    count = 0
    while (i+2 < measurements.size) {
        // Sum elements i through i+2 to create sum of elements e.g. window.
        val window = measurements[i] +
                measurements[i+1] +
                measurements[i+2]

        if (prevWindow != 0 && window > prevWindow) {
            count++
        }
        prevWindow = window
        i++
    }

    println("Answer to Part 02! The count is: ${count}.")
}