package skaro.pokeapi.resource.type;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;

public class TypeRelations {

	private List<NamedApiResource<Type>> noDamageTo;
	private List<NamedApiResource<Type>> halfDamageTo;
	private List<NamedApiResource<Type>> doubleDamageTo;
	private List<NamedApiResource<Type>> noDamagefrom;
	private List<NamedApiResource<Type>> halfDamagefrom;
	private List<NamedApiResource<Type>> doubleDamagefrom;
	
	public List<NamedApiResource<Type>> getNoDamageTo() {
		return noDamageTo;
	}
	public void setNoDamageTo(List<NamedApiResource<Type>> noDamageTo) {
		this.noDamageTo = noDamageTo;
	}
	public List<NamedApiResource<Type>> getHalfDamageTo() {
		return halfDamageTo;
	}
	public void setHalfDamageTo(List<NamedApiResource<Type>> halfDamageTo) {
		this.halfDamageTo = halfDamageTo;
	}
	public List<NamedApiResource<Type>> getDoubleDamageTo() {
		return doubleDamageTo;
	}
	public void setDoubleDamageTo(List<NamedApiResource<Type>> doubleDamageTo) {
		this.doubleDamageTo = doubleDamageTo;
	}
	public List<NamedApiResource<Type>> getNoDamagefrom() {
		return noDamagefrom;
	}
	public void setNoDamagefrom(List<NamedApiResource<Type>> noDamagefrom) {
		this.noDamagefrom = noDamagefrom;
	}
	public List<NamedApiResource<Type>> getHalfDamagefrom() {
		return halfDamagefrom;
	}
	public void setHalfDamagefrom(List<NamedApiResource<Type>> halfDamagefrom) {
		this.halfDamagefrom = halfDamagefrom;
	}
	public List<NamedApiResource<Type>> getDoubleDamagefrom() {
		return doubleDamagefrom;
	}
	public void setDoubleDamagefrom(List<NamedApiResource<Type>> doubleDamagefrom) {
		this.doubleDamagefrom = doubleDamagefrom;
	}
	
}
