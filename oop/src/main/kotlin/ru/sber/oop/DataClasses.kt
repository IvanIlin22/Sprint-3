package ru.sber.oop

import java.util.*

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun hashCode(): Int {
        return Objects.hash(name, age, city);
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || javaClass != other.javaClass) return false;
        val user = other as User;
        return user.name == name && user.age == age && user.city == city;
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "Kate");
    user1.city = "Omsk";
    val user3 = user1.copy();
    user3.city = "Tomsk";
}
