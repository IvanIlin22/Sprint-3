package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt(0, 100);

    fun attack(opponent: Fightable): Int
}

class Player(override val powerType: String,
             override var healthPoints: Int,
             val name: String,
             val isBlessed: Boolean) : Fightable {

    override val damageRoll: Int = super.damageRoll;

    override fun attack(opponent: Fightable): Int {
        if (isBlessed) {
            opponent.healthPoints -= damageRoll * 2;
            return damageRoll * 2;
        }
        opponent.healthPoints -= damageRoll;

        return damageRoll;
    }
}

abstract class Monster(override val powerType: String,
                       override var healthPoints: Int,
                       val name: String,
                       val description: String) : Fightable {

    override val damageRoll: Int = super.damageRoll;

    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll;

        return damageRoll;
    }

    fun getSalutation(): String {

        return "Hello I'm monster";
    }
}

class Goblin(powerType: String,
             healthPoints: Int,
             name: String,
             description: String) : Monster(powerType, healthPoints, name, description) {

    override val damageRoll: Int
        get() = super.damageRoll / 2;
}
