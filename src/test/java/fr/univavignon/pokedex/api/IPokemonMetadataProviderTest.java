package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokemonMetadataProviderTest {

	IPokemonMetadataProvider testPokemonMetadataProvider;

	@Before
    public void setUp() throws PokedexException {

		testPokemonMetadataProvider = mock(IPokemonMetadataProvider.class);

        when(testPokemonMetadataProvider.getPokemonMetadata(anyInt())).thenAnswer(new Answer<PokemonMetadata>() {
			public PokemonMetadata answer(InvocationOnMock invocation) throws PokedexException {
				Object[] args = invocation.getArguments();
        		int index = (int) args[0];
        		if(index < 0 || index > 151) {
        			throw new PokedexException("Unknown Pokemon");
        		} else if (index == 1) {
            		return new PokemonMetadata(index, "Bulbasaur", 126, 126, 90);
        		} else {
        			return new PokemonMetadata(index, "Vaporeon", 186, 168, 260);
        		}
        	}
        });
    }

	@Test
	public void getPokemonMetadataTest() throws PokedexException {
		assertThrows(PokedexException.class, () -> {testPokemonMetadataProvider.getPokemonMetadata(-1);});
		assertThrows(PokedexException.class, () -> {testPokemonMetadataProvider.getPokemonMetadata(152);});

		PokemonMetadata pokemon1Metadata = testPokemonMetadataProvider.getPokemonMetadata(1);
		assertEquals(1, pokemon1Metadata.getIndex());
		assertEquals("Bulbasaur", pokemon1Metadata.getName());
		assertEquals(126, pokemon1Metadata.getAttack());
		assertEquals(126, pokemon1Metadata.getDefense());
		assertEquals(90, pokemon1Metadata.getStamina());

		PokemonMetadata pokemon134Metadata = testPokemonMetadataProvider.getPokemonMetadata(134);
		assertEquals(134, pokemon134Metadata.getIndex());
		assertEquals("Vaporeon", pokemon134Metadata.getName());
		assertEquals(186, pokemon134Metadata.getAttack());
		assertEquals(168, pokemon134Metadata.getDefense());
		assertEquals(260, pokemon134Metadata.getStamina());
	}

}
