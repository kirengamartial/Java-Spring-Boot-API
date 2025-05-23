package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTests {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_CreatePokemon_ReturnPokemonDto() {
        Pokemon pokemon = Pokemon.builder()
                .name("Pickachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("Pickachu")
                .type("electric")
                .build();

        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto savedPokemon = pokemonService.createPokemon(pokemonDto);
        Assertions.assertThat(savedPokemon).isNotNull();
    }

    @Test
    public void PokemonService_GetAllPokemon_ReturnResponseDto(){
        Page<Pokemon> pokemons = Mockito.mock(Page.class);
        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

        PokemonResponse savePokemon = pokemonService.getAllPokemons(1, 10);

        Assertions.assertThat(savePokemon).isNotNull();
    }

    @Test
    public void PokemonService_GetPokemonById_ReturnPokemonDto() {
        Pokemon pokemon = Pokemon.builder()
                .name("Pickachu")
                .type("electric")
                .build();


        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        PokemonDto savedPokemon = pokemonService.getPokemonById(1);
        Assertions.assertThat(savedPokemon).isNotNull();
    }

    @Test
    public void PokemonService_UpdatePokemon_ReturnPokemonDto() {
        Pokemon pokemon = Pokemon.builder()
                .name("Pickachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("Pickachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto savedPokemon = pokemonService.updatePokemon(pokemonDto, 1);
        Assertions.assertThat(savedPokemon).isNotNull();
    }

    @Test
    public void PokemonService_DeletePokemonById_ReturnPokemonDto() {
        Pokemon pokemon = Pokemon.builder()
                .name("Pickachu")
                .type("electric")
                .build();


        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        assertAll(() -> pokemonService.deletePokemon(1));
    }

}
