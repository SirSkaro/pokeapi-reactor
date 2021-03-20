package skaro.pokeapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import skaro.pokeapi.client.MapEndpointRegistry;
import skaro.pokeapi.client.PokeApiEndpointRegistry;
import skaro.pokeapi.resource.ability.Ability;
import skaro.pokeapi.resource.berry.Berry;
import skaro.pokeapi.resource.berryfirmness.BerryFirmness;
import skaro.pokeapi.resource.berryflavor.BerryFlavor;
import skaro.pokeapi.resource.characteristic.Characteristic;
import skaro.pokeapi.resource.contesteffect.ContestEffect;
import skaro.pokeapi.resource.contesttype.ContestType;
import skaro.pokeapi.resource.egggroup.EggGroup;
import skaro.pokeapi.resource.encountercondition.EncounterCondition;
import skaro.pokeapi.resource.encounterconditionvalue.EncounterConditionValue;
import skaro.pokeapi.resource.encountermethod.EncounterMethod;
import skaro.pokeapi.resource.evolutionchain.EvolutionChain;
import skaro.pokeapi.resource.evolutiontrigger.EvolutionTrigger;
import skaro.pokeapi.resource.gender.Gender;
import skaro.pokeapi.resource.generation.Generation;
import skaro.pokeapi.resource.growthrate.GrowthRate;
import skaro.pokeapi.resource.item.Item;
import skaro.pokeapi.resource.itemattribute.ItemAttribute;
import skaro.pokeapi.resource.itemcategory.ItemCategory;
import skaro.pokeapi.resource.itemflingeffect.ItemFlingEffect;
import skaro.pokeapi.resource.itempocket.ItemPocket;
import skaro.pokeapi.resource.language.Language;
import skaro.pokeapi.resource.location.Location;
import skaro.pokeapi.resource.locationarea.LocationArea;
import skaro.pokeapi.resource.machine.Machine;
import skaro.pokeapi.resource.move.Move;
import skaro.pokeapi.resource.moveailment.MoveAilment;
import skaro.pokeapi.resource.movecategory.MoveCategory;
import skaro.pokeapi.resource.movedamageclass.MoveDamageClass;
import skaro.pokeapi.resource.movelearnmethod.MoveLearnMethod;
import skaro.pokeapi.resource.movetarget.MoveTarget;
import skaro.pokeapi.resource.nature.Nature;
import skaro.pokeapi.resource.pokedex.Pokedex;
import skaro.pokeapi.resource.pokemon.Pokemon;
import skaro.pokeapi.resource.pokemoncolor.PokemonColor;
import skaro.pokeapi.resource.pokemonform.PokemonForm;
import skaro.pokeapi.resource.pokemonhabitat.PokemonHabitat;
import skaro.pokeapi.resource.pokemonshape.PokemonShape;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;
import skaro.pokeapi.resource.region.Region;
import skaro.pokeapi.resource.stat.Stat;
import skaro.pokeapi.resource.supercontesteffect.SuperContestEffect;
import skaro.pokeapi.resource.type.Type;
import skaro.pokeapi.resource.version.Version;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

@Configuration
public class PokeApiReactorEndpointConfiguration {

	@Bean
	public PokeApiEndpointRegistry endpointRegistry() {
		Map<Class<?>, String> endpoints = new HashMap<>();
		endpoints.put(Ability.class, "ability");
		endpoints.put(Berry.class, "berry");
		endpoints.put(BerryFirmness.class, "berry-firmness");
		endpoints.put(BerryFlavor.class, "berry-flavor");
		endpoints.put(Characteristic.class, "characteristic");
		endpoints.put(ContestEffect.class, "contest-effect");
		endpoints.put(ContestType.class, "contest-type");
		endpoints.put(EggGroup.class, "egg-group");
		endpoints.put(EncounterCondition.class, "encounter-condition");
		endpoints.put(EncounterConditionValue.class, "encounter-condition-value");
		endpoints.put(EncounterMethod.class, "encounter-method");
		endpoints.put(EvolutionChain.class, "evolution-chain");
		endpoints.put(EvolutionTrigger.class, "evolution-trigger");
		endpoints.put(Gender.class, "gender");
		endpoints.put(Generation.class, "generation");
		endpoints.put(GrowthRate.class, "growth-rate");
		endpoints.put(Item.class, "item");
		endpoints.put(ItemAttribute.class, "item-attribute");
		endpoints.put(ItemCategory.class, "item-category");
		endpoints.put(ItemFlingEffect.class, "item-fling-effect");
		endpoints.put(ItemPocket.class, "item-pocket");
		endpoints.put(Language.class, "language");
		endpoints.put(Location.class, "location");
		endpoints.put(LocationArea.class, "location-area");
		endpoints.put(Machine.class, "machine");
		endpoints.put(Move.class, "move");
		endpoints.put(MoveAilment.class, "move-ailment");
		endpoints.put(MoveCategory.class, "move-category");
		endpoints.put(MoveDamageClass.class, "move-damage-class");
		endpoints.put(MoveLearnMethod.class, "move-learn-method");
		endpoints.put(MoveTarget.class, "move-target");
		endpoints.put(Nature.class, "nature");
		endpoints.put(Pokedex.class, "pokdex");
		endpoints.put(Pokemon.class, "pokemon");
		endpoints.put(PokemonColor.class, "pokemon-color");
		endpoints.put(PokemonForm.class, "pokemon-form");
		endpoints.put(PokemonHabitat.class, "pokemon-habitat");
		endpoints.put(PokemonShape.class, "pokemon-shape");
		endpoints.put(PokemonSpecies.class, "pokemon-species");
		endpoints.put(Region.class, "pokemon-region");
		endpoints.put(Stat.class, "stat");
		endpoints.put(SuperContestEffect.class, "super-contest-effect");
		endpoints.put(Type.class, "type");
		endpoints.put(Version.class, "version");
		endpoints.put(VersionGroup.class, "version-group");
		
		return new MapEndpointRegistry(endpoints);
	}
	
}
