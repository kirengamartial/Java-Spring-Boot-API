package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


//    @GetMapping("pokemon")
//    public ResponseEntity<List<Pokemon>> getPokemon() {
//        List<Pokemon> pokemons = new ArrayList<>();
//        pokemons.add(new Pokemon(1, "squirtle", "water"));
//        pokemons.add(new Pokemon(2, "Pickachu", "Electric"));
//        pokemons.add(new Pokemon(3, "Charmande", "Fire"));
//        return ResponseEntity.ok(pokemons);
//    }

@GetMapping("pokemon")
public ResponseEntity<List<PokemonDto>> getPokemon() {
    return new ResponseEntity<List<PokemonDto>>(pokemonService.getAllPokemons(), HttpStatus.OK);
}

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id) {
        return  ResponseEntity.ok(pokemonService.getPokemonById(id));
    }
//    @GetMapping("pokemon/{id}")
//    public Pokemon pokemonDetail(@PathVariable int id) {
//        return new Pokemon(id, "Squirtle", "Water");
//    }

//    @PostMapping("pokemon/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
//        System.out.println(pokemon.getName());
//        System.out.println(pokemon.getType());
//        return new ResponseEntity<Pokemon>(pokemon, HttpStatus.CREATED);
//    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
       return new ResponseEntity<PokemonDto>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable int id) {
        PokemonDto response = pokemonService.updatePokemon(pokemonDto, id);
        return new ResponseEntity<PokemonDto>(response, HttpStatus.OK);
    }

//    @PutMapping("pokemon/{id}/update")
//    public ResponseEntity<Pokemon> updatePokemon(@PathVariable int id, @RequestBody Pokemon pokemon) {
//        System.out.println(pokemon.getName());
//        System.out.println(pokemon.getType());
//
//        return ResponseEntity.ok(pokemon);
//    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable int id) {
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon deleted", HttpStatus.OK);
    }


//    @DeleteMapping("pokemon/{id}/delete")
//    public ResponseEntity<String> deletePokemon(@PathVariable int id) {
//        System.out.println(id);
//        return ResponseEntity.ok("Pokemon Deleted successfully");
//    }
}
