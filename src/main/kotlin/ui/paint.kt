package ui

import entities.Coordinates
import entities.Entity
import utilities.TILE_SIZE
import java.awt.Graphics

fun paintPlayArea(g: Graphics, playArea: Entity) {
    // Will paint a surrounding area with a border, as -1 and rows/cols. are outside
    for (row in -1..playArea.rows) {
        for (column in -1..playArea.columns) {
            val elementColor = playArea.get(row, column)

            // Empty element
            if (elementColor == 0) continue

            g.color = getTileColor(elementColor)
            g.fillRect((column + 2) * TILE_SIZE, (row + 2) * TILE_SIZE, TILE_SIZE, TILE_SIZE)
        }
    }
}

fun paintPiece(g: Graphics, piece: Entity, offset: Coordinates) {
    for (row in 0 until piece.rows) {
        for (column in 0 until piece.columns) {
            val elementColor = piece.get(row, column)

            // Empty cell in container
            if (elementColor == 0) {
                g.color = getTileColor(99)
            } else {
                g.color = getTileColor(elementColor)
            }

            val (x, y) = ((column + offset.column + 2) * TILE_SIZE) to (row + offset.row + 2) * TILE_SIZE

            g.fillRect(x, y, TILE_SIZE, TILE_SIZE)
        }
    }
}
