package utilities

import entities.Entity

fun handlePieceRotation(piece: Entity): Entity {
    val size = piece.rows
    val rotated = Entity(size, size)

    for (rowIndex in 0 until size) {
        for (columnIndex in 0 until size) {
            val element = piece.get(rowIndex, columnIndex)

            rotated.set(columnIndex, size - 1 - rowIndex, element)
        }
    }

    return rotated
}