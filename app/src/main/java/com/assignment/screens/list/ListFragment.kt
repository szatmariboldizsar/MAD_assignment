package com.assignment.screens.list

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.database.PokemonDatabase
import com.assignment.databinding.ListFragmentBinding
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: ListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.list_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = PokemonDatabase.getInstance(application).pokemonDao
        val viewModelFactory = ListViewModelFactory(dataSource, application)

        val listViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ListViewModel::class.java)


        binding.listViewModel = listViewModel

        val adapter = PokemonAdapter()
        binding.pokemonList.adapter = adapter

       listViewModel.pokemons.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        return binding.root
    }
}