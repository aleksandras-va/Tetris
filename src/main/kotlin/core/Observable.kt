package core

interface IOnNotify {
    fun handleEvent(name: String)
}

open class Observable {
    private val observers = ArrayList<IOnNotify>()

    fun onNotify(eventName: String) {
        observers.forEach { it.handleEvent(eventName) }
    }

    fun register(observer: IOnNotify) {
        observers.add(observer)
    }
}
