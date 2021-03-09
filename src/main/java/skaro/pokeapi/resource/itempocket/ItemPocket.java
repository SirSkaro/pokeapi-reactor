package skaro.pokeapi.resource.itempocket;

import java.util.List;

import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.itemcategory.ItemCategory;

public class ItemPocket {

	private Integer id;
	private String name;
	private List<NamedApiResource<ItemCategory>> categories;
	private List<Name> names;
	
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
	public List<NamedApiResource<ItemCategory>> getCategories() {
		return categories;
	}
	public void setCategories(List<NamedApiResource<ItemCategory>> categories) {
		this.categories = categories;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
}
