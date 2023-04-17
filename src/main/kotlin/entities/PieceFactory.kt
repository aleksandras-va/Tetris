package entities

enum class Shape {
    I, O, J, L, S, T, Z
}

class PieceFactory {
    private fun create(shape: Shape, color: Int): Entity {
        var bounds = Coordinates(3, 3)

        if (shape == Shape.I || shape == Shape.O) {
            bounds = Coordinates(4, 4)
        }

        val piece = Entity(bounds.row, bounds.column)

        when (shape) {
            Shape.I -> createIPiece(piece, color)
            Shape.O -> createOPiece(piece, color)
            Shape.J -> createJPiece(piece, color)
            Shape.L -> createLPiece(piece, color)
            Shape.S -> createSPiece(piece, color)
            Shape.T -> createTPiece(piece, color)
            Shape.Z -> createZPiece(piece, color)
        }

        return piece
    }

    private fun createIPiece(piece: Entity, randomColor: Int) {
        piece.set(2, 0, randomColor)
        piece.set(2, 1, randomColor)
        piece.set(2, 2, randomColor)
        piece.set(2, 3, randomColor)
    }

    private fun createOPiece(piece: Entity, randomColor: Int) {
        piece.set(1, 1, randomColor)
        piece.set(1, 2, randomColor)
        piece.set(2, 1, randomColor)
        piece.set(2, 2, randomColor)
    }

    private fun createJPiece(piece: Entity, randomColor: Int) {
        piece.set(0, 1, randomColor)
        piece.set(1, 1, randomColor)
        piece.set(2, 0, randomColor)
        piece.set(2, 1, randomColor)
    }

    private fun createLPiece(piece: Entity, randomColor: Int) {
        piece.set(0, 1, randomColor)
        piece.set(1, 1, randomColor)
        piece.set(2, 1, randomColor)
        piece.set(2, 2, randomColor)
    }

    private fun createSPiece(piece: Entity, randomColor: Int) {
        piece.set(1, 1, randomColor)
        piece.set(1, 2, randomColor)
        piece.set(2, 0, randomColor)
        piece.set(2, 1, randomColor)
    }

    private fun createTPiece(piece: Entity, randomColor: Int) {
        piece.set(1, 0, randomColor)
        piece.set(1, 1, randomColor)
        piece.set(1, 2, randomColor)
        piece.set(2, 1, randomColor)
    }

    private fun createZPiece(piece: Entity, randomColor: Int) {
        piece.set(1, 0, randomColor)
        piece.set(1, 1, randomColor)
        piece.set(2, 1, randomColor)
        piece.set(2, 2, randomColor)
    }

    fun getRandomPiece(): Entity {
        return create(Shape.values().random(), (1..5).random())
    }
}