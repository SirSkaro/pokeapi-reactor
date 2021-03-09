package skaro.pokeapi.resource.move;

import java.util.List;

import skaro.pokeapi.resource.ApiResource;
import skaro.pokeapi.resource.MachineVersionDetail;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.VerboseEffect;
import skaro.pokeapi.resource.ability.AbilityEffectChange;
import skaro.pokeapi.resource.contesteffect.ContestEffect;
import skaro.pokeapi.resource.contesttype.ContestType;
import skaro.pokeapi.resource.generation.Generation;
import skaro.pokeapi.resource.movedamageclass.MoveDamageClass;
import skaro.pokeapi.resource.movetarget.MoveTarget;
import skaro.pokeapi.resource.supercontesteffect.SuperContestEffect;
import skaro.pokeapi.resource.type.Type;

public class Move {

	private Integer id;
	private String name;
	private Integer accuracy;
	private Integer effectChance;
	private Integer pp;
	private Integer priority;
	private Integer power;
	private ContestComboSets contestCombos;
	private NamedApiResource<ContestType> contestType;
	private ApiResource<ContestEffect> contestEffect;
	private NamedApiResource<MoveDamageClass> damageClass;
	private List<VerboseEffect> effectEntries;
	private List<AbilityEffectChange> effectChanges;
	private List<MoveFlavorText> flavorTextEntries;
	private NamedApiResource<Generation> generation;
	private List<MachineVersionDetail> machines;
	private MoveMetaData meta;
	private List<Name> names;
	private List<PastMoveStatValues> pastValues;
	private List<MoveStatChange> statChanges;
	private ApiResource<SuperContestEffect> superContestEffect;
	private NamedApiResource<MoveTarget> target;
	private NamedApiResource<Type> type;
	
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
	public Integer getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	public Integer getEffectChance() {
		return effectChance;
	}
	public void setEffectChance(Integer effectChance) {
		this.effectChance = effectChance;
	}
	public Integer getPp() {
		return pp;
	}
	public void setPp(Integer pp) {
		this.pp = pp;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public ContestComboSets getContestCombos() {
		return contestCombos;
	}
	public void setContestCombos(ContestComboSets contestCombos) {
		this.contestCombos = contestCombos;
	}
	public NamedApiResource<ContestType> getContestType() {
		return contestType;
	}
	public void setContestType(NamedApiResource<ContestType> contestType) {
		this.contestType = contestType;
	}
	public ApiResource<ContestEffect> getContestEffect() {
		return contestEffect;
	}
	public void setContestEffect(ApiResource<ContestEffect> contestEffect) {
		this.contestEffect = contestEffect;
	}
	public NamedApiResource<MoveDamageClass> getDamageClass() {
		return damageClass;
	}
	public void setDamageClass(NamedApiResource<MoveDamageClass> damageClass) {
		this.damageClass = damageClass;
	}
	public List<VerboseEffect> getEffectEntries() {
		return effectEntries;
	}
	public void setEffectEntries(List<VerboseEffect> effectEntries) {
		this.effectEntries = effectEntries;
	}
	public List<AbilityEffectChange> getEffectChanges() {
		return effectChanges;
	}
	public void setEffectChanges(List<AbilityEffectChange> effectChanges) {
		this.effectChanges = effectChanges;
	}
	public List<MoveFlavorText> getFlavorTextEntries() {
		return flavorTextEntries;
	}
	public void setFlavorTextEntries(List<MoveFlavorText> flavorTextEntries) {
		this.flavorTextEntries = flavorTextEntries;
	}
	public NamedApiResource<Generation> getGeneration() {
		return generation;
	}
	public void setGeneration(NamedApiResource<Generation> generation) {
		this.generation = generation;
	}
	public List<MachineVersionDetail> getMachines() {
		return machines;
	}
	public void setMachines(List<MachineVersionDetail> machines) {
		this.machines = machines;
	}
	public MoveMetaData getMeta() {
		return meta;
	}
	public void setMeta(MoveMetaData meta) {
		this.meta = meta;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public List<PastMoveStatValues> getPastValues() {
		return pastValues;
	}
	public void setPastValues(List<PastMoveStatValues> pastValues) {
		this.pastValues = pastValues;
	}
	public List<MoveStatChange> getStatChanges() {
		return statChanges;
	}
	public void setStatChanges(List<MoveStatChange> statChanges) {
		this.statChanges = statChanges;
	}
	public ApiResource<SuperContestEffect> getSuperContestEffect() {
		return superContestEffect;
	}
	public void setSuperContestEffect(ApiResource<SuperContestEffect> superContestEffect) {
		this.superContestEffect = superContestEffect;
	}
	public NamedApiResource<MoveTarget> getTarget() {
		return target;
	}
	public void setTarget(NamedApiResource<MoveTarget> target) {
		this.target = target;
	}
	public NamedApiResource<Type> getType() {
		return type;
	}
	public void setType(NamedApiResource<Type> type) {
		this.type = type;
	}
	
}
