package core

import java.awt.event.KeyEvent
import javax.swing.Timer

class GameTimer(private val onTimerEnd: () -> Unit) {
    private var timer: Timer? = null
    private var tickCounter = 0
    private val interval = 10
    val keyPressRegister = BooleanArray(256)

    fun start() {
        if (timer == null) {
            timer = Timer(interval) { onTick() }
            timer?.start()
        }
    }

    fun stop() {
        timer?.stop()
        timer = null
    }

    private fun onTick() {
        if (keyPressRegister[KeyEvent.VK_DOWN]) {
            tickCounter += 100
        } else {
            tickCounter += 1
        }

        if (tickCounter > 200) {
            onTimerEnd()
            tickCounter = 0
        }
    }

}
