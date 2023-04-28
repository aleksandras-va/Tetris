package input

import core.Game
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyListener(private val game: Game) : KeyListener {
    override fun keyPressed(event: KeyEvent) {
        game.keyDown(event.keyCode)
        when (event.keyCode) {
            KeyEvent.VK_DOWN -> game.movePiece("down")
            KeyEvent.VK_LEFT -> game.movePiece("left")
            KeyEvent.VK_RIGHT -> game.movePiece("right")
            KeyEvent.VK_UP -> game.rotatePiece()
        }
    }

    override fun keyReleased(event: KeyEvent) {
        game.keyUp(event.keyCode)
    }

    override fun keyTyped(event: KeyEvent) {}
}