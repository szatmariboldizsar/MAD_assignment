/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.assignment.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.assignment.R
import com.assignment.databinding.GameFragmentBinding
import com.assignment.screens.game.GameViewModel
import com.assignment.screens.title.TitleFragmentDirections
import kotlinx.android.synthetic.main.game_fragment.*
import java.util.*

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        binding.pokemonImage.setImageResource(
            when (viewModel.pokemon.value) {
                "onix" -> R.drawable.onix
                "mewtwo" -> R.drawable.mewtwo
                "clefairy" -> R.drawable.clefairy
                else -> R.drawable.pikachu
            }
        )
        binding.hintButton.setOnClickListener {
            pokemon_name.visibility = View.VISIBLE
        }
        binding.apply {
            pokemonName.visibility = View.GONE
        }
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) gameFinished()
        })
        binding.enterButton.setOnClickListener {
            viewModel.onInput(input_text.text.toString().toLowerCase(Locale.ROOT))
            input_text.setText("")
            val drawableResource = when (viewModel.pokemon.value) {
                "onix" -> R.drawable.onix
                "mewtwo" -> R.drawable.mewtwo
                "clefairy" -> R.drawable.clefairy
                else -> R.drawable.pikachu
            }
            binding.pokemonImage.setImageResource(drawableResource)
        }
        return binding.root
    }


    private fun gameFinished() {
        Toast.makeText(
            activity,
            "Game has just finished",
            Toast.LENGTH_LONG
        )
            .show()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }
}
