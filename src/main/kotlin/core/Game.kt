package core

import entities.Coordinates
import entities.Entity
import entities.PieceFactory
import ui.Panel
import utilities.handlePieceRotation

class Game(private val gamePanel: Panel) {
    fun start() {
        spawnPiece()
    }

    fun movePiece(direction: String) {
        var offset = gamePanel.offset

        when (direction) {
            "up" -> offset = Coordinates(offset.row - 1, offset.column)
            "down" -> offset = Coordinates(offset.row + 1, offset.column)
            "left" -> offset = Coordinates(offset.row, offset.column - 1)
            "right" -> offset = Coordinates(offset.row, offset.column + 1)
        }

        if (intersects(gamePanel.piece!!, offset)) {
            if (direction == "down") {
                applyPiece(gamePanel.piece!!)
                restartPiece()
            }
            return
        }

        gamePanel.offset = offset
        gamePanel.repaint()
    }

    fun rotatePiece() {
        gamePanel.piece = handlePieceRotation(gamePanel.piece!!)

        if (intersects(gamePanel.piece!!, gamePanel.offset)) {
            return
        }

        gamePanel.repaint()
    }

    private fun spawnPiece() {
        gamePanel.piece = PieceFactory().getRandomPiece()
        gamePanel.offset = Coordinates(0, 0)
        gamePanel.repaint()
    }

    private fun applyPiece(piece: Entity) {
        for (row in 0 until piece.rows) {
            for (column in 0 until piece.columns) {
                val pieceColor = piece.get(row, column)

                if (pieceColor == 0) continue

                gamePanel.playArea.set(row + gamePanel.offset.row, column + gamePanel.offset.column, pieceColor)
            }
        }
    }

    private fun restartPiece() {
        spawnPiece()
    }

    private fun intersects(piece: Entity, offset: Coordinates): Boolean {
        val playArea = gamePanel.playArea

        for (row in 0 until piece.rows) {
            for (column in 0 until piece.columns) {
                val pieceColor = piece.get(row, column)

                if (pieceColor == 0) continue

                val mapColor = playArea.get(row + offset.row, column + offset.column)

                if (mapColor != 0) return true
            }
        }

        return false
    }
}

