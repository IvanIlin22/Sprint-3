package ru.sber.oop

open class Room(val name: String, val size: Int) {

    constructor(name: String): this(name, 100)

    val monster: Monster = Goblin("physical", 1000, "Green Goblin", "Attack with bombs");

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = monster.getSalutation();

}

open class TownSquare(name: String, size: Int) : Room(name, size) {

    override val dangerLevel = super.dangerLevel - 3;

    final override fun load(): String {

        return "Here you can look at the fountain and monuments";
    }
}
