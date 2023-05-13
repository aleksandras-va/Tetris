package core

import entities.Coordinates
import entities.Entity
import entities.PieceFactory
import ui.Panel
import utilities.handlePieceRotation
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.Timer

class Game(private val gamePanel: Panel) : Observable(), IOnNotify {
    private val keyPressRegister = BooleanArray(256)
    private var tickCounter = 0


    init {
        register(gamePanel)
        register(this)
    }

    fun start() {
        spawnPiece()

        val timer = Timer(10) { _: ActionEvent -> onTimer() }

        timer.start()
    }

    private fun movePiece(direction: String) {
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
        onNotify("repaint")
    }

    private fun rotatePiece() {
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
        keyPressRegister[keyCode] = true

        when (keyCode) {
            KeyEvent.VK_LEFT -> movePiece("left")
            KeyEvent.VK_RIGHT -> movePiece("right")
            KeyEvent.VK_UP -> rotatePiece()
        }
    }

    fun keyUp(keyCode: Int) {
        keyPressRegister[keyCode] = false
    }

    private fun onTimer() {
        if (keyPressRegister[KeyEvent.VK_DOWN]) {
            tickCounter += 100
        } else {
            tickCounter += 1
        }

        if (tickCounter <= 200) {
            return
        }

        tickCounter = 0

        onNotify("timerExpired")
    }

    override fun handleEvent(name: String) {
        if (name == "timerExpired") {
            movePiece("down")
        }
    }
}

