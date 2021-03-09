package skaro.pokeapi.resource.evolutionchain;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.evolutiontrigger.EvolutionTrigger;
import skaro.pokeapi.resource.item.Item;
import skaro.pokeapi.resource.location.Location;
import skaro.pokeapi.resource.move.Move;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;
import skaro.pokeapi.resource.type.Type;

public class EvolutionDetail {

	private NamedApiResource<Item> item;
	private NamedApiResource<EvolutionTrigger> trigger;
	private Integer gender;
	private NamedApiResource<Item> heldItem;
	private NamedApiResource<Move> knownMove;
	private NamedApiResource<Type> knownMoveType;
	private NamedApiResource<Location> location;
	private Integer minLevel;
	private Integer minHappiness;
	private Integer minBeauty;
	private Integer minAffection;
	private Boolean needsOverworldRain;
	private NamedApiResource<PokemonSpecies> partySpecies;
	private NamedApiResource<Type> partyType;
	private Integer relativePhysicalStats;
	private String timeOfDay;
	private NamedApiResource<PokemonSpecies> tradeSpecies;
	private Boolean turnUpsideDown;
	
	public NamedApiResource<Item> getItem() {
		return item;
	}
	public void setItem(NamedApiResource<Item> item) {
		this.item = item;
	}
	public NamedApiResource<EvolutionTrigger> getTrigger() {
		return trigger;
	}
	public void setTrigger(NamedApiResource<EvolutionTrigger> trigger) {
		this.trigger = trigger;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public NamedApiResource<Item> getHeldItem() {
		return heldItem;
	}
	public void setHeldItem(NamedApiResource<Item> heldItem) {
		this.heldItem = heldItem;
	}
	public NamedApiResource<Move> getKnownMove() {
		return knownMove;
	}
	public void setKnownMove(NamedApiResource<Move> knownMove) {
		this.knownMove = knownMove;
	}
	public NamedApiResource<Type> getKnownMoveType() {
		return knownMoveType;
	}
	public void setKnownMoveType(NamedApiResource<Type> knownMoveType) {
		this.knownMoveType = knownMoveType;
	}
	public NamedApiResource<Location> getLocation() {
		return location;
	}
	public void setLocation(NamedApiResource<Location> location) {
		this.location = location;
	}
	public Integer getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}
	public Integer getMinHappiness() {
		return minHappiness;
	}
	public void setMinHappiness(Integer minHappiness) {
		this.minHappiness = minHappiness;
	}
	public Integer getMinBeauty() {
		return minBeauty;
	}
	public void setMinBeauty(Integer minBeauty) {
		this.minBeauty = minBeauty;
	}
	public Integer getMinAffection() {
		return minAffection;
	}
	public void setMinAffection(Integer minAffection) {
		this.minAffection = minAffection;
	}
	public Boolean getNeedsOverworldRain() {
		return needsOverworldRain;
	}
	public void setNeedsOverworldRain(Boolean needsOverworldRain) {
		this.needsOverworldRain = needsOverworldRain;
	}
	public NamedApiResource<PokemonSpecies> getPartySpecies() {
		return partySpecies;
	}
	public void setPartySpecies(NamedApiResource<PokemonSpecies> partySpecies) {
		this.partySpecies = partySpecies;
	}
	public NamedApiResource<Type> getPartyType() {
		return partyType;
	}
	public void setPartyType(NamedApiResource<Type> partyType) {
		this.partyType = partyType;
	}
	public Integer getRelativePhysicalStats() {
		return relativePhysicalStats;
	}
	public void setRelativePhysicalStats(Integer relativePhysicalStats) {
		this.relativePhysicalStats = relativePhysicalStats;
	}
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public NamedApiResource<PokemonSpecies> getTradeSpecies() {
		return tradeSpecies;
	}
	public void setTradeSpecies(NamedApiResource<PokemonSpecies> tradeSpecies) {
		this.tradeSpecies = tradeSpecies;
	}
	public Boolean getTurnUpsideDown() {
		return turnUpsideDown;
	}
	public void setTurnUpsideDown(Boolean turnUpsideDown) {
		this.turnUpsideDown = turnUpsideDown;
	}
	
}
