package entities

class Entity(val rows: Int, val columns: Int) {
    // 2D array of rows x columns [rows][columns]
    private val matrix = Array(rows) { IntArray(columns) }

    fun get(row: Int, column: Int): Int {
        // Out of bounds left, top
        if (row < 0 || column < 0) {
            return -1
        }

        // Out of bounds right, button
        if (row >= rows || column >= columns) {
            return -1
        }

        return matrix[row][column]
    }

    fun set(row: Int, column: Int, value: Int) {
        matrix[row][column] = value
    }
}

fun intersects(gameMap: Entity, piece: Entity, offsetRow: Int, offsetColumn: Int): Boolean {
    for (r in 0 until piece.rows) {
        for (c in 0 until piece.columns) {
            val pieceColor = piece.get(r, c)

            if (pieceColor == 0) {
                continue
            }

            val mapColor = gameMap.get(r + offsetRow, c + offsetColumn)

            if (mapColor != 0) {
                return true
            }

        }
    }

    return false
}

fun applyPiece(gameMap: Entity, piece: Entity, offsetRow: Int, offsetColumn: Int) {
    for (r in 0 until piece.rows) {
        for (c in 0 until piece.columns) {
            val pieceColor = piece.get(r, c)

            if (pieceColor == 0) {
                continue
            }

            gameMap.set(r + offsetRow, c + offsetColumn, pieceColor)
        }
    }
}

