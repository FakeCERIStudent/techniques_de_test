package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokedexTest {

	IPokedex testPokedex;
	List<Pokemon> pokemonList;

	@Before
    public void setUp() throws PokedexException {

		testPokedex = mock(IPokedex.class);
		pokemonList = new ArrayList<Pokemon>();

        Pokemon pokemon1 = new Pokemon(1, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon pokemon134 = new Pokemon(134, "Vaporeon", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        pokemonList.add(pokemon1);
        pokemonList.add(pokemon134);

        when(testPokedex.size()).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) {
        		return pokemonList.size();
        	}
        });
        when(testPokedex.addPokemon(any())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) {
        		Object[] args = invocation.getArguments();
        		Pokemon pokemonToAdd = (Pokemon) args[0];
        		pokemonList.add(pokemonToAdd);
        		return pokemonToAdd.getIndex();
        	}
        });
        when(testPokedex.getPokemon(anyInt())).thenAnswer(new Answer<Pokemon>() {
			public Pokemon answer(InvocationOnMock invocation) throws PokedexException {
        		Object[] args = invocation.getArguments();
        		int indexToFind = (int) args[0];
        		for (Pokemon pokemon : pokemonList) {
        		    if(pokemon.getIndex() == indexToFind) {
        		    	return pokemon;
        		    }
        		}
        		throw new PokedexException("Pokemon not found");
        	}
        });
        when(testPokedex.getPokemons()).thenReturn(pokemonList);
        when(testPokedex.getPokemons(any())).thenAnswer(new Answer<List<Pokemon>>() {
        	@SuppressWarnings("unchecked")
			public List<Pokemon> answer(InvocationOnMock invocation) {
        		Object[] args = invocation.getArguments();
        		List<Pokemon> sortedList = pokemonList.stream().collect(Collectors.toList());
        		Collections.sort(pokemonList, (Comparator<Pokemon>)args[0]);
        		return sortedList;
        	}
        });
    }

	@Test
	public void addPokemonAndSizeTest() {
		int sizeBefore = testPokedex.size();
        Pokemon pokemon134 = new Pokemon(134, "Vaporeon", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
		int index = testPokedex.addPokemon(pokemon134);
		int sizeAfter = testPokedex.size();
		assertEquals(index, pokemon134.getIndex());
		assertEquals(sizeBefore + 1, sizeAfter);
	}

	@Test
	public void getPokemonTest() throws PokedexException {
		Pokemon pokemon1 = testPokedex.getPokemon(1);
		assertEquals(pokemon1.getIndex(), 1);
		Pokemon pokemon134 = testPokedex.getPokemon(134);
		assertEquals(pokemon134.getIndex(), 134);
		assertThrows(PokedexException.class, () -> {testPokedex.getPokemon(15);});
	}

	@Test
	public void getPokemonsTest() {
		List<Pokemon> pokemons = testPokedex.getPokemons();
		assertEquals(pokemons.size(), 2);
	}

	@Test
	public void getSortedPokemonsTest() {
		PokemonComparators pokemonComparator = PokemonComparators.INDEX;
		List<Pokemon> pokemons = testPokedex.getPokemons(pokemonComparator);
		assertEquals(pokemons.size(), 2);
		assertEquals(pokemons.get(0).getIndex(), 1);
		assertEquals(pokemons.get(1).getIndex(), 134);
	}
}
