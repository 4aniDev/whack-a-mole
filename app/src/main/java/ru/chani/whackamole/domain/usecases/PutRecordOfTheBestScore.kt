package ru.chani.whackamole.domain.usecases

import ru.chani.whackamole.domain.Repository
import ru.chani.whackamole.domain.entity.GameResult

class PutRecordOfTheBestScore(private val repository: Repository) {
    operator fun invoke(gameResult: GameResult) {
        repository.putRecordOfTheBestScore(gameResult)
    }
}