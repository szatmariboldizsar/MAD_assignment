package com.assignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {

    @Insert
    suspend fun insert(pokemon: Pokemon)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Query("SELECT * from pokemon_table WHERE Id = :key")
    suspend fun get(key: Long): Pokemon

    @Query("DELETE FROM pokemon_table")
    suspend fun clear()

    @Query("INSERT INTO pokemon_table(name)  VALUES(:name)")
    fun insertManual(name: String)

    @Query("SELECT * FROM pokemon_table ORDER BY Id DESC")
    fun getAllPokemon(): LiveData<List<Pokemon>>

    @Query("SELECT * from pokemon_table WHERE Id = :key")
    fun getPokemonWithId(key: Long): LiveData<Pokemon>
}