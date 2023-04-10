package entities

enum class Shape {
    I, O, J, L, S, T, Z
}

class PieceFactory {
    fun create(shape: Shape, color: Int): Entity {
        var bounds = Coordinates(3, 3)

        if (shape == Shape.I || shape == Shape.O) {
            bounds = Coordinates(4, 4)
        }

        val piece = Entity(bounds.row, bounds.column)

        when (shape) {
            Shape.I -> createIPiece(piece, color)
            Shape.O -> TODO()
            Shape.J -> TODO()
            Shape.L -> TODO()
            Shape.S -> TODO()
            Shape.T -> createTPiece(piece, color)
            Shape.Z -> TODO()
        }

        return piece
    }

    private fun createIPiece(piece: Entity, randomColor: Int) {
        piece.set(2, 0, randomColor)
        piece.set(2, 1, randomColor)
        piece.set(2, 2, randomColor)
        piece.set(2, 3, randomColor)
    }

    private fun createTPiece(piece: Entity, randomColor: Int) {
        piece.set(1, 0, randomColor)
        piece.set(1, 1, randomColor)
        piece.set(1, 2, randomColor)
        piece.set(2, 1, randomColor)

    }
}