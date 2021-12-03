import java.io.File

fun main() {
    println("Hello Day 02...")

    val startingPosition = Position(x = 0, y = 0, z = 0)

    val factory = InstructionFactory()
    // Solution to part (1)
    val instructions = File("day-02/instructions.txt")

    var position = startingPosition

    instructions.forEachLine {
        position = factory.create(it)
            .apply(position)

        println("Current position (x,y,z): (${position.x}, ${position.y}, ${position.z})")
    }

    println("Solution to part 1: horizontal * depth = ${position.x * position.y}")
}

data class Position(val x: Int, val y: Int, val z: Int)

interface Instruction {
    fun apply(position: Position): Position
}

abstract class AbstractInstruction(val value: Int): Instruction

class ForwardInstruction(x: Int) : AbstractInstruction(x) {

    override fun apply(position: Position) = Position(x = position.x + value, y = position.y + (position.z * value), z = position.z)
}

class UpInstruction(y: Int) : AbstractInstruction(y) {

    override fun apply(position: Position) = Position(x = position.x, y = position.y, z = position.z - value)

}

class DownInstruction(y: Int) : AbstractInstruction(y) {

    override fun apply(position: Position) = Position(x = position.x, y = position.y, z = position.z + value)
}

class InstructionFactory {

    companion object  {
        private const val FORWARD = "forward"
        private const val UP = "up"
        private const val DOWN = "down"
    }


    fun create(instruction: String): Instruction {
        val instructionsArray = instruction.split(" ")
        val command = instructionsArray[0]
        val value = instructionsArray[1].toInt()

        return when(command) {
            FORWARD -> ForwardInstruction(value)
            DOWN -> DownInstruction(value)
            UP -> UpInstruction(value)
            else -> throw IllegalArgumentException("No such command: ${command}.")
        }
    }
}





