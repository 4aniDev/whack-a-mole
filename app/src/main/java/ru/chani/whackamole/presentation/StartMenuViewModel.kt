package ru.chani.whackamole.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.chani.whackamole.data.RepositoryImp
import ru.chani.whackamole.domain.usecases.GetRecordOfTheBestScore

class StartMenuViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImp(application)

    private val getRecordOfTheBestScore = GetRecordOfTheBestScore(repository)



    fun getBestScore() = getRecordOfTheBestScore()
}