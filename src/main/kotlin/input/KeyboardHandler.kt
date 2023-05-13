package input

import core.Game
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardHandler(private val game: Game) : KeyListener {
    override fun keyPressed(event: KeyEvent) {
        game.keyDown(event.keyCode)
    }

    override fun keyReleased(event: KeyEvent) {
        game.keyUp(event.keyCode)
    }

    override fun keyTyped(event: KeyEvent) {}
}
