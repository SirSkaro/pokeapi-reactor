package skaro.pokeapi.resource;

public class NamedAPiResourceList<T> {

	private Integer count;
	private String next;
	private Boolean previous;
	private NamedApiResource<T> result;
	
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
	public NamedApiResource<T> getResult() {
		return result;
	}
	public void setResult(NamedApiResource<T> result) {
		this.result = result;
	}
	
}
