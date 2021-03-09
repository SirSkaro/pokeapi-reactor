package skaro.pokeapi.resource.item;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.pokemon.Pokemon;

public class ItemHolderPokemon {

	private NamedApiResource<Pokemon> pokemon;
	private List<ItemHolderPokemonVersionDetail> versionDetails;
	
	public NamedApiResource<Pokemon> getPokemon() {
		return pokemon;
	}
	public void setPokemon(NamedApiResource<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}
	public List<ItemHolderPokemonVersionDetail> getVersionDetails() {
		return versionDetails;
	}
	public void setVersionDetails(List<ItemHolderPokemonVersionDetail> versionDetails) {
		this.versionDetails = versionDetails;
	}
	
}
