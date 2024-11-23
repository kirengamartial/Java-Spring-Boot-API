package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.models.Pokemon;

import java.util.List;

public interface PokemonService {
   PokemonDto createPokemon(PokemonDto pokemonDto);
   List<PokemonDto> getAllPokemons();
   PokemonDto getPokemonById(int id);
   PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
   void deletePokemon(int id);
}
