package core

import entities.Coordinates
import entities.Entity
import entities.PieceFactory
import ui.Panel
import utilities.handlePieceRotation
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent.VK_DOWN
import javax.swing.Timer

// TODO: spawn piece in middle
// TODO: death
// TODO: line detection
// TODO: wall bounce
// TODO: reset button
// TODO: drop piece

class Game(private val gamePanel: Panel) {
    fun start() {
        spawnPiece()

        val timer = Timer(10) { event: ActionEvent -> onTimer() }

        timer.start()
    }

    private val keysDown = BooleanArray(256)
    private var framesUntilZero = 200

    private fun onTimer() {


        if (keysDown[VK_DOWN]) {
            framesUntilZero -= 10;
        }

        if (--framesUntilZero > 0) {
            return
        }

        framesUntilZero = 200



        movePiece("down")
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

    fun keyDown(keyCode: Int) {
        keysDown[keyCode] = true;
    }

    fun keyUp(keyCode: Int) {
        keysDown[keyCode] = false;
    }
}

