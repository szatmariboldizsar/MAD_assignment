package com.assignment.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    companion object {
        private const val DONE = 0L

        private const val ONE_SECOND = 1000L

        private const val COUNTDOWN_TIME = 60000L
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }
    private val timer: CountDownTimer

    // The current pokemon
    private val _pokemon = MutableLiveData<String>()
    val pokemon: LiveData<String>
        get() = _pokemon

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

        }
        timer.start()
        _pokemon.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created")
        resetList()
        nextPokemon()
    }

    // The list of words - the front of the list is the next word to guess
    lateinit var pokemonList: MutableList<String>

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        pokemonList = mutableListOf(
            "pikachu",
            "onix",
            "mewtwo",
            "clefairy"
        )
        pokemonList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextPokemon() {
        if (pokemonList.isEmpty()) {
            onGameFinish()
        } else {
            _pokemon.value = pokemonList.removeAt(0)

        }
    }

    fun onInput(input: String) {
        if (input == _pokemon.value) {
            _score.value = (score.value)?.plus(1)
            nextPokemon()
        }
        else {
            nextPokemon()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }
}