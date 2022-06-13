package ru.chani.whackamole.domain.usecases

import ru.chani.whackamole.domain.Repository

class GetRecordOfTheBestScore(private val repository: Repository) {
    operator fun invoke(): String {
        return repository.getRecordOfTerBestScore().score.toString()
    }
}