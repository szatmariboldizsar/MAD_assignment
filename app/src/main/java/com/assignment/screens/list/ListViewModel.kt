package com.assignment.screens.list

import android.app.Application
import androidx.lifecycle.*
import com.assignment.database.PokemonDao
import kotlinx.coroutines.launch

class ListViewModel(dataSource: PokemonDao,
                    application: Application): ViewModel() {
    val database = dataSource

    val pokemons = database.getAllPokemon()

    init {
        initializeDatabase()
    }

    private fun initializeDatabase(){
        viewModelScope.launch { database.clear()
            database.insertManual("Pikachu")
            database.insertManual("Squirtle")
            database.insertManual("Bulbasaur")
            database.insertManual("Onix")
            database.insertManual("Mewtwo")
            database.insertManual("Abomasnow")
            database.insertManual("Abra")
            database.insertManual("Absol")
            database.insertManual("Accelgor")
            database.insertManual("Aegislash")
            database.insertManual("Aerodactyl")
            database.insertManual("Aggron")
            database.insertManual("Aipom")
            database.insertManual("Alakazam")
            database.insertManual("Alcremie")
            database.insertManual("Alomomola")
            database.insertManual("Altaria")
            database.insertManual("Amaura")
            database.insertManual("Ambipom")
            database.insertManual("Amoonguss")
            database.insertManual("Ampharos")
            database.insertManual("Anorith")
            database.insertManual("Appletun")
            database.insertManual("Applin")
            database.insertManual("Araquanid")
            database.insertManual("Arbok")
            database.insertManual("Arcanine")
            database.insertManual("Arceus")
            database.insertManual("Archen")
            database.insertManual("Archeops")
            database.insertManual("Arctovish")
            database.insertManual("Arctozolt")
            database.insertManual("Ariados")
            database.insertManual("Aromatisse")
            database.insertManual("Aron")
            database.insertManual("Arrokuda")
            database.insertManual("Articuno")
            database.insertManual("Audino")
            database.insertManual("Aurorus")
            database.insertManual("Avalugg")
            database.insertManual("Axew")
            database.insertManual("Azelf")
            database.insertManual("Azumarill")
            database.insertManual("Azurill")
            database.insertManual("Bagon")
            database.insertManual("Baltoy")
            database.insertManual("Banette")
            database.insertManual("Barbaracle")
            database.insertManual("Barboach")
            database.insertManual("Barraskewda")
            database.insertManual("Basculegion")
            database.insertManual("Basculin")
            database.insertManual("Bastiodon")
            database.insertManual("Bayleef")
            database.insertManual("Beartic")
            database.insertManual("Beautifly")
            database.insertManual("Beedrill")
            database.insertManual("Beheeyem")
            database.insertManual("Beldum")
            database.insertManual("Bellossom")
            database.insertManual("Bellsprout")
            database.insertManual("Bergmite")
            database.insertManual("Bewear")
            database.insertManual("Bibarel")
            database.insertManual("Bidoof")
            database.insertManual("Binacle")
            database.insertManual("Bisharp")
            database.insertManual("Blacephalon")
            database.insertManual("Blastoise")
            database.insertManual("Blaziken")
            database.insertManual("Blipbug")
            database.insertManual("Blissey")
            database.insertManual("Blitzle")
            database.insertManual("Boldore")
            database.insertManual("Boltund")
            database.insertManual("Bonsly")
            database.insertManual("Bouffalant")
            database.insertManual("Bounsweet")
            database.insertManual("Braixen")
            database.insertManual("Braviary")
            database.insertManual("Breloom")
            database.insertManual("Brionne")
            database.insertManual("Bronzong")
            database.insertManual("Bronzor")
            database.insertManual("Bruxish")
            database.insertManual("Budew")
            database.insertManual("Buizel")
            database.insertManual("Buneary")
            database.insertManual("Bunnelby")
            database.insertManual("Burmy")
            database.insertManual("Butterfree")
            database.insertManual("Buzzwole")
        }
    }
}