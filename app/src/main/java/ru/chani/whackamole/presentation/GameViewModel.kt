package ru.chani.whackamole.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.chani.whackamole.data.RepositoryImp
import ru.chani.whackamole.domain.entity.GameResult
import ru.chani.whackamole.domain.usecases.GetRandomMolePosition
import ru.chani.whackamole.domain.usecases.GetRecordOfTheBestScore
import ru.chani.whackamole.domain.usecases.PutRecordOfTheBestScore

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImp(application)

    private val getRandomMolePosition = GetRandomMolePosition()
    private val putRecordOfTheBestScore = PutRecordOfTheBestScore(repository)
    private val getRecordOfTheBestScore = GetRecordOfTheBestScore(repository)

    private var timer: CountDownTimer? = null

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _currentPositionOfMole = MutableLiveData<Int>()
    val currentPositionOfMole: LiveData<Int>
        get() = _currentPositionOfMole

    private var _currentScore = MutableLiveData<String>().apply { value = DEFAULT_SCORE.toString() }
    val currentScore: LiveData<String>
        get() = _currentScore

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult


    private var currentScoreInt = DEFAULT_SCORE

    fun startGame() {
        startGameTimer()
        startRespawnMoleTimer()
    }

    private fun startGameTimer() {
        timer = object : CountDownTimer(
            GAME_TIME_IN_MILLIS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }


    private fun startRespawnMoleTimer() {
        timer = object : CountDownTimer(
            GAME_TIME_IN_MILLIS,
            TIME_IN_MILLIS_FOR_RESPAWN_OF_MOLE
        ) {
            override fun onTick(millisUntilFinished: Long) {
                updatePositionOfMole()
            }

            override fun onFinish() {}
        }
        timer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        return seconds.toString()
    }


    private fun formatScore(currentScore: Int): String {
        return currentScore.toString()
    }

    fun updatePositionOfMole() {
        _currentPositionOfMole.value = getRandomMolePosition()
    }

    fun selectedHole(numberOfSelectedHole: Int) {
        if (numberOfSelectedHole == currentPositionOfMole.value) {
            currentScoreInt++
            _currentScore.value = formatScore(currentScoreInt)
            updatePositionOfMole()
        }
    }

    private fun finishGame() {
        compareResult()
        _gameResult.value = GameResult(score = currentScoreInt)
    }

    private fun compareResult() {
        if (currentScoreInt > getRecordOfTheBestScore().toInt()) {
            putRecordOfTheBestScore(GameResult(currentScoreInt))
        }
    }



    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val GAME_TIME_IN_MILLIS = 30000L

        private const val TIME_IN_MILLIS_FOR_RESPAWN_OF_MOLE = 500L

        private const val  DEFAULT_SCORE = 0
    }
}