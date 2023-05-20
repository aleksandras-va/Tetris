package core

import ui.Panel
import java.awt.event.KeyEvent

class Game(gamePanel: Panel) : Observable(), INotifyObservers {
    private val pieceManager = PieceManager(gamePanel, this)
    
    private val gameTimer = GameTimer {
        notify("timerExpired")
    }

    init {
        register(gamePanel)
        register(this)
        register(pieceManager)

    }

    fun start() {
        gameTimer.start()
        notify("gameStarted")
    }

    fun keyDown(keyCode: Int) {
        gameTimer.keyPressRegister[keyCode] = true

        when (keyCode) {
            KeyEvent.VK_LEFT -> pieceManager.movePiece("left")
            KeyEvent.VK_RIGHT -> pieceManager.movePiece("right")
            KeyEvent.VK_UP -> pieceManager.rotatePiece()
        }
    }

    fun keyUp(keyCode: Int) {
        gameTimer.keyPressRegister[keyCode] = false
    }


    override fun onNotification(name: String) {
        if (name == "timerExpired") {
            pieceManager.movePiece("down")
        }

        if (name == "pieceLanded") {
            println("--- Piece Landed ---")
        }
    }
}

