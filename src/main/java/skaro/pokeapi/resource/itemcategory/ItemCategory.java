package skaro.pokeapi.resource.itemcategory;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.item.Item;
import skaro.pokeapi.resource.itempocket.ItemPocket;

public class ItemCategory implements PokeApiResource {

	private Integer id;
	private String name;
	private List<NamedApiResource<Item>> items;
	private List<Name> names;
	private NamedApiResource<ItemPocket> pocket;
	
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
	public List<NamedApiResource<Item>> getItems() {
		return items;
	}
	public void setItems(List<NamedApiResource<Item>> items) {
		this.items = items;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public NamedApiResource<ItemPocket> getPocket() {
		return pocket;
	}
	public void setPocket(NamedApiResource<ItemPocket> pocket) {
		this.pocket = pocket;
	}
	
}
