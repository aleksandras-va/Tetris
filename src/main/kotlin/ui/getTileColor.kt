package ui

import java.awt.Color

fun getTileColor(pieceColor: Int): Color {
    return when (pieceColor) {
        -1 -> Color(43, 45, 66) // Grey
        1 -> Color(3, 65, 174) // Blue
        2 -> Color(114, 203, 59) // Green
        3 -> Color(255, 213, 0) // Yellow
        4 -> Color(255, 151, 28) // Orange
        5 -> Color(255, 50, 19) // Red
        else -> Color(220, 220, 232) // Light grey
    }
}