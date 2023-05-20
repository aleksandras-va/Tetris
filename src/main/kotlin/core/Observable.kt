package core

interface INotifyObservers {
    fun onNotification(name: String)
}

open class Observable {
    private val observers = ArrayList<INotifyObservers>()

    fun notify(eventName: String) {
        observers.forEach { it.onNotification(eventName) }
    }

    fun register(observer: INotifyObservers) {
        observers.add(observer)
    }
}
