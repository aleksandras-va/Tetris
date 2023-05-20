package core

import ui.Panel
import java.awt.event.KeyEvent
import javax.swing.Timer

class Game(gamePanel: Panel) : Observable(), IOnNotify {
    private val pieceManager = PieceManager(gamePanel, this)
    private val keyPressRegister = BooleanArray(256)
    private var tickCounter = 0

    init {
        register(gamePanel)
        register(this)
    }

    fun start() {
        pieceManager.spawnPiece()

        val timer = Timer(10) { onTimer() }

        timer.start()

        onNotify("gameStarted")
    }

    fun keyDown(keyCode: Int) {
        keyPressRegister[keyCode] = true

        when (keyCode) {
            KeyEvent.VK_LEFT -> pieceManager.movePiece("left")
            KeyEvent.VK_RIGHT -> pieceManager.movePiece("right")
            KeyEvent.VK_UP -> pieceManager.rotatePiece()
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
            pieceManager.movePiece("down")
        }

        if (name == "Ping") println("Pong")
    }
}

