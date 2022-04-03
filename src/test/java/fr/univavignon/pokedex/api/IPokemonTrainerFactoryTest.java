package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokemonTrainerFactoryTest {

	IPokemonTrainerFactory testPokemonTrainerFactory;
	IPokedexFactory pokedexFactory;
	
	@Before
    public void setUp() {

		testPokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
		pokedexFactory = mock(IPokedexFactory.class);

        when(testPokemonTrainerFactory.createTrainer(
        		anyString(), any(), any()
        )).thenAnswer(new Answer<PokemonTrainer>() {
			public PokemonTrainer answer(InvocationOnMock invocation) {
        		Object[] args = invocation.getArguments();
        		String name = (String) args[0];
        		Team team = (Team) args[1];
        		IPokedex pokedex = mock(IPokedex.class);
        		when(pokedex.size()).thenReturn(0);
        		return new PokemonTrainer(name, team, pokedex);
        	}
        });
    }

	@Test
	public void createTrainerTest() {
		PokemonTrainer pokemonTrainer = testPokemonTrainerFactory.createTrainer("Blue", Team.MYSTIC, pokedexFactory);
		assertEquals("Blue", pokemonTrainer.getName());
		assertEquals(Team.MYSTIC, pokemonTrainer.getTeam());
		assertNotNull(pokemonTrainer.getPokedex());
		assertEquals(0, pokemonTrainer.getPokedex().size());
	}

}
