package entities

enum class Shape {
    I, O, J, L, S, T, Z
}

val shapeMap = mapOf(
    Shape.I to arrayOf(
        intArrayOf(2, 0),
        intArrayOf(2, 1),
        intArrayOf(2, 2),
        intArrayOf(2, 3)
    ),

    Shape.O to arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 1),
        intArrayOf(2, 2)
    ),

    Shape.J to arrayOf(
        intArrayOf(1, 0),
        intArrayOf(1, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 2)
    ),

    Shape.L to arrayOf(
        intArrayOf(1, 0),
        intArrayOf(1, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 0)
    ),

    Shape.S to arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 1),
        intArrayOf(2, 0),
        intArrayOf(2, 1)
    ),

    Shape.T to arrayOf(
        intArrayOf(1, 0),
        intArrayOf(1, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 1)
    ),

    Shape.Z to arrayOf(
        intArrayOf(1, 0),
        intArrayOf(1, 1),
        intArrayOf(2, 1),
        intArrayOf(2, 2)
    )
)


class PieceFactory {
    private fun create(shape: Shape): Entity {
        var bounds = Coordinates(3, 3)

        // Make container 3x3 or 4x4
        if (shape == Shape.I || shape == Shape.O) {
            bounds = Coordinates(4, 4)
        }

        val piece = Entity(bounds.row, bounds.column)

        return fillPiece(piece, shape)
    }

    private fun fillPiece(piece: Entity, shape: Shape): Entity {
        val map = shapeMap.getValue(shape)
        val color = when (shape) {
            Shape.I -> 1
            Shape.O -> 2
            Shape.J -> 3
            Shape.L -> 4
            Shape.S -> 5
            Shape.T -> 6
            Shape.Z -> 7
        }

        for (row in map) {
            piece.set(row[0], row[1], color)
        }

        return piece
    }

    fun getRandomPiece(): Entity {
        return create(Shape.values().random())
    }
}