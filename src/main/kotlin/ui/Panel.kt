package ui

import core.INotifyObservers
import entities.Coordinates
import entities.Entity
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel


class Panel(val playArea: Entity) : JPanel(), INotifyObservers {
    var piece: Entity? = null
    var offset = Coordinates(0, 0)

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        paintPlayArea(g, playArea)

        piece?.let { paintPiece(g, it, offset) }

        // Draw grid
        g.color = Color.LIGHT_GRAY

        for (x in 0..width step 32) {
            g.drawLine(x, 0, x, height)
        }

        for (y in 0..height step 32) {
            g.drawLine(0, y, width, y)
        }
    }

    override fun onNotification(name: String) {
        if (name != "repaint") {
            return
        }

        repaint()
    }
}


