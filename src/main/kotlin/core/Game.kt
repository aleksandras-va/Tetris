package core

import entities.Coordinates
import entities.PieceFactory
import entities.Shape
import ui.Panel
import utilities.handlePieceRotation

class Game(private val gamePanel: Panel) {
    fun start() {
        gamePanel.activePiece = PieceFactory().create(Shape.T, 1)
        gamePanel.repaint()
    }

    fun movePiece(direction: String) {
        var offset = gamePanel.offset

        when (direction) {
            "up" -> offset = Coordinates(offset.row - 1, offset.column)
            "down" -> offset = Coordinates(offset.row + 1, offset.column)
            "left" -> offset = Coordinates(offset.row, offset.column - 1)
            "right" -> offset = Coordinates(offset.row, offset.column + 1)
        }

        gamePanel.offset = offset
        gamePanel.repaint()
    }

    fun rotatePiece() {
        gamePanel.activePiece = handlePieceRotation(gamePanel.activePiece!!)
        gamePanel.repaint()
    }
}