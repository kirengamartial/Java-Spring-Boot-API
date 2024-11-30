package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon() {
        // Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();

        // Act
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        // Assert
        assertNotNull(savedPokemon);
        assertTrue(savedPokemon.getId() > 0);
    }

    @Test
    public void PokemonRepository_findAll_ReturnMoreThanOnePokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();
        Pokemon pokemon2 = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();
    pokemonRepository.save(pokemon);
    pokemonRepository.save(pokemon2);

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        assertNotNull(pokemonList);
        assertTrue(pokemonList.size() == 2);

    }

    @Test
    public void PokemonRepository_findById_ReturnPokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemonList = pokemonRepository.findById(pokemon.getId()).get();
        assertNotNull(pokemonList);

    }

    @Test
    public void PokemonRepository_findByType_ReturnPokemonNotNull() {
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemonList = pokemonRepository.findByType(pokemon.getType()).get();
        assertNotNull(pokemonList);

    }

    @Test
    public void PokemonRepository_UpdatePokemon_ReturnPokemonNotNull() {
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemonSave = pokemonRepository.findById(pokemon.getId()).get();
        pokemonSave.setType("electric");
        pokemonSave.setName("Raichu");

        Pokemon updatedPokemon = pokemonRepository.save(pokemonSave);

        assertNotNull(updatedPokemon.getName());
        assertNotNull(updatedPokemon.getType());

    }

    @Test
    public void PokemonRepository_PokemonReturn_ReturnPokemonIsEmpty() {
        Pokemon pokemon = Pokemon.builder()
                .name("pickachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

         pokemonRepository.deleteById(pokemon.getId());
        Optional<Pokemon> pokemonReturn = pokemonRepository.findById(pokemon.getId());
        assertTrue(pokemonReturn.isEmpty(), "pokemonReturn should be empty after deletion");

    }
}