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



