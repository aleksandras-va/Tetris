package ui

import java.awt.Color

fun getTileColor(pieceColor: Int): Color {
    return when (pieceColor) {
        -1 -> Color(43, 45, 66) // Grey
        1 -> Color(88, 170, 155) // Cyan I
        2 -> Color(247, 184, 1) // Yellow O
        3 -> Color(4, 75, 127) // Blue J
        4 -> Color(255, 107, 0) // Orange L
        5 -> Color(95, 173, 65) // Green S
        6 -> Color(86, 22, 67) // Purple T
        7 -> Color(172, 14, 35) // Red Z
        else -> Color(220, 220, 232) // Light grey
    }
}