package ru.chani.whackamole.domain

import ru.chani.whackamole.domain.entity.GameResult

interface Repository {
    fun putRecordOfTheBestScore(gameResult: GameResult)
    fun getRecordOfTerBestScore(): GameResult

}