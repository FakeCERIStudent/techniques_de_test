package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokemonFactoryTest {

	IPokemonFactory testPokemonFactory;
	@Before
    public void setUp() {

		testPokemonFactory = mock(IPokemonFactory.class);
		when(testPokemonFactory.createPokemon(
        		anyInt(), anyInt(), anyInt(), anyInt(), anyInt())
        ).thenAnswer(new Answer<Pokemon>() {
			public Pokemon answer(InvocationOnMock invocation) {
        		Object[] args = invocation.getArguments();
        		int cp = (int) args[0];
        		int hp = (int) args[1];
        		int dust = (int) args[2];
        		int candy = (int) args[3];
        		return new Pokemon(1, "Bulbasaur", 126, 126, 90, cp, hp, dust, candy, 0.56);
        	}
        });
        
    }

	@Test
	public void createPokemonTest() {
		Pokemon pokemon = testPokemonFactory.createPokemon(1, 613, 64, 4000, 4);
		assertEquals(1, pokemon.getIndex());
		assertEquals("Bulbasaur", pokemon.getName());
		assertEquals(0.56, pokemon.getIv(), 0.01);
	}

}
