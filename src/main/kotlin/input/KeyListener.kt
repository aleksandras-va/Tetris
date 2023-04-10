package input

import core.Game
import ui.Panel
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyListener(private val game: Game) : KeyListener {
    override fun keyPressed(event: KeyEvent) {
        when (event.keyCode) {
            KeyEvent.VK_UP -> game.movePiece("up")
            KeyEvent.VK_DOWN -> game.movePiece("down")
            KeyEvent.VK_LEFT -> game.movePiece("left")
            KeyEvent.VK_RIGHT -> game.movePiece("right")
            KeyEvent.VK_ENTER -> game.rotatePiece()
        }
    }

    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}
}