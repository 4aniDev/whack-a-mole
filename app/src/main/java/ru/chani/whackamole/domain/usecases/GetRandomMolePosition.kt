package ru.chani.whackamole.domain.usecases

import kotlin.random.Random

class GetRandomMolePosition {
    operator fun invoke(): Int {
        return Random.nextInt(STARTING_POSITION_OF_MOLE, FINAL_POSITION_OF_MOLE)
    }

    companion object {
        private val STARTING_POSITION_OF_MOLE = 1
        private val FINAL_POSITION_OF_MOLE = 9
    }
}