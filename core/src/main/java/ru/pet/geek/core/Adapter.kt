package ru.pet.geek.core

interface Adapter<in FROM, out TO> {
    fun adapt(from: FROM): TO
}