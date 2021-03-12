package skaro.pokeapi.resource.berry;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.berryfirmness.BerryFirmness;
import skaro.pokeapi.resource.item.Item;
import skaro.pokeapi.resource.type.Type;

public class Berry implements PokeApiResource {

	private Integer id;
	private String name;
	private Integer growthTime;
	private Integer maxHarvest;
	private Integer naturalGiftPower;
	private Integer size;
	private Integer smoothness;
	private Integer soilDryness;
	private NamedApiResource<BerryFirmness> firmness;
	private List<BerryFlavorMap> flavors;
	private NamedApiResource<Item> item;
	private NamedApiResource<Type> naturalGiftType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGrowthTime() {
		return growthTime;
	}
	public void setGrowthTime(Integer growthTime) {
		this.growthTime = growthTime;
	}
	public Integer getMaxHarvest() {
		return maxHarvest;
	}
	public void setMaxHarvest(Integer maxHarvest) {
		this.maxHarvest = maxHarvest;
	}
	public Integer getNaturalGiftPower() {
		return naturalGiftPower;
	}
	public void setNaturalGiftPower(Integer naturalGiftPower) {
		this.naturalGiftPower = naturalGiftPower;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getSmoothness() {
		return smoothness;
	}
	public void setSmoothness(Integer smoothness) {
		this.smoothness = smoothness;
	}
	public Integer getSoilDryness() {
		return soilDryness;
	}
	public void setSoilDryness(Integer soilDryness) {
		this.soilDryness = soilDryness;
	}
	public NamedApiResource<BerryFirmness> getFirmness() {
		return firmness;
	}
	public void setFirmness(NamedApiResource<BerryFirmness> firmness) {
		this.firmness = firmness;
	}
	public List<BerryFlavorMap> getFlavors() {
		return flavors;
	}
	public void setFlavors(List<BerryFlavorMap> flavors) {
		this.flavors = flavors;
	}
	public NamedApiResource<Item> getItem() {
		return item;
	}
	public void setItem(NamedApiResource<Item> item) {
		this.item = item;
	}
	public NamedApiResource<Type> getNaturalGiftType() {
		return naturalGiftType;
	}
	public void setNaturalGiftType(NamedApiResource<Type> naturalGiftType) {
		this.naturalGiftType = naturalGiftType;
	}
	
}
