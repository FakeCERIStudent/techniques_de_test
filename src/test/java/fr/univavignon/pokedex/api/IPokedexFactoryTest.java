package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokedexFactoryTest {

	IPokedexFactory testPokedexFactory;
	IPokemonMetadataProvider metadataProvider;
	IPokemonFactory pokemonFactory;
	
	@Before
    public void setUp() {

		testPokedexFactory = mock(IPokedexFactory.class);
		metadataProvider = mock(IPokemonMetadataProvider.class);
		pokemonFactory = mock(IPokemonFactory.class);

        when(testPokedexFactory.createPokedex(any(), any())).thenAnswer(new Answer<IPokedex>() {
			public IPokedex answer(InvocationOnMock invocation) {
        		IPokedex pokedex = mock(IPokedex.class);
        		when(pokedex.size()).thenReturn(0);
        		return pokedex;
        	}
        });
    }

	@Test
	public void createPokedexTest() {
		IPokedex pokedex =  testPokedexFactory.createPokedex(metadataProvider, pokemonFactory);
		assertEquals(0, pokedex.size());
	}

}
