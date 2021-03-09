package skaro.pokeapi.resource.characteristic;

import java.util.List;

public class Characteristic {

	private Integer id;
	private Integer geneModulo;
	private List<Integer> possibleValues;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGeneModulo() {
		return geneModulo;
	}
	public void setGeneModulo(Integer geneModulo) {
		this.geneModulo = geneModulo;
	}
	public List<Integer> getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(List<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
