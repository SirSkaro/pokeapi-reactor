package skaro.pokeapi.resource;

import java.util.List;

public class NamedApiResourceList<T extends PokeApiResource> {

	private Integer count;
	private String next;
	private Boolean previous;
	private List<NamedApiResource<T>> results;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public Boolean getPrevious() {
		return previous;
	}
	public void setPrevious(Boolean previous) {
		this.previous = previous;
	}
	public List<NamedApiResource<T>> getResults() {
		return results;
	}
	public void setResults(List<NamedApiResource<T>> results) {
		this.results = results;
	}
	
}
