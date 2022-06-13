package ru.chani.whackamole.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.chani.whackamole.data.RepositoryImp
import ru.chani.whackamole.domain.usecases.GetRecordOfTheBestScore

class GameFinishedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImp(context = application)
    private val getRecordOfTheBestScore = GetRecordOfTheBestScore(repository)



    fun getBestScore(): String {
        return getRecordOfTheBestScore()
    }

}