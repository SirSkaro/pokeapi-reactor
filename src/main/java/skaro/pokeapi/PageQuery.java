package skaro.pokeapi;

public class PageQuery {

	private Integer limit;
	private Integer offset;
	
	public PageQuery(int limit, int offset) {
		this.limit = limit;
		this.offset = offset;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
