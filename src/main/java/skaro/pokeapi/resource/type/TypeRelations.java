package skaro.pokeapi.resource.type;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;

public class TypeRelations {

	private List<NamedApiResource<Type>> noDamageTo;
	private List<NamedApiResource<Type>> halfDamageTo;
	private List<NamedApiResource<Type>> doubleDamageTo;
	private List<NamedApiResource<Type>> noDamageFrom;
	private List<NamedApiResource<Type>> halfDamageFrom;
	private List<NamedApiResource<Type>> doubleDamageFrom;
	
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
	public List<NamedApiResource<Type>> getNoDamageFrom() {
		return noDamageFrom;
	}
	public void setNoDamageFrom(List<NamedApiResource<Type>> noDamageFrom) {
		this.noDamageFrom = noDamageFrom;
	}
	public List<NamedApiResource<Type>> getHalfDamageFrom() {
		return halfDamageFrom;
	}
	public void setHalfDamageFrom(List<NamedApiResource<Type>> halfDamageFrom) {
		this.halfDamageFrom = halfDamageFrom;
	}
	public List<NamedApiResource<Type>> getDoubleDamageFrom() {
		return doubleDamageFrom;
	}
	public void setDoubleDamageFrom(List<NamedApiResource<Type>> doubleDamageFrom) {
		this.doubleDamageFrom = doubleDamageFrom;
	}
	
}
