package core

import entities.Entity
import input.KeyListener
import ui.Panel
import javax.swing.JFrame

class Window : JFrame("Tetris") {
    private lateinit var gamePanel: Panel
    private lateinit var game: Game

    init {
        setupGamePanel()
    }

    private fun setupGamePanel() {
        gamePanel = Panel(Entity(20, 10))
        game = Game(gamePanel)

        gamePanel.addKeyListener(KeyListener(game))
        gamePanel.isFocusable = true

        contentPane.add(gamePanel)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true

        setSize(800, 800)
    }

    fun startGame() {
        game.start()
    }

    fun stopGame() {}
}
