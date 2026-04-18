package game.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.graphic.shared.Colors;
import game.logic.board.Position;
import game.logic.scripts.ScriptType;
import game.logic.scripts.Script;
import game.logic.scripts.all.standard.CommonScripts;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Entity {
    protected String name;

    protected double hp;
    protected double maxHp;

    protected double baseAttack;
    protected double maxBaseAttack;

    protected double baseDefense;
    protected double maxBaseDefense;

    @JsonIgnore
    protected List<Script> scripts;

    protected List<ScriptType> scriptTypes;

    protected double xp;
    protected double nextLevelXp;

    protected int points;

    protected int level;

    protected boolean enraged;

    @JsonIgnore
    private Position position;

    public Entity(double maxHp, double baseAttack, double baseDefense, String name, List<Script> scripts, double xp, int level, int points) {
        this.maxHp = checkValue(maxHp);
        this.hp = checkValue(maxHp);
        this.maxBaseAttack = checkValue(baseAttack);
        this.maxBaseDefense = checkValue(baseDefense);
        this.baseAttack = checkValue(baseAttack);
        this.baseDefense = checkValue(baseDefense);
        this.name = name == null ? "No name" : name;
        this.scripts = scripts;
        this.position = new Position(-1, -1);
        this.level = level;
        this.points = points;
        this.nextLevelXp = calcXpNeeded();
        this.xp = xp;
        this.enraged = false;
        updateScriptTypes();
    }

    /**
     * Constructor for new Entity.
     * @param maxHp max hp of the entity
     * @param baseAttack base attack of the entity
     * @param baseDefense base defense of the entity
     * @param name name of the entity
     */
    public Entity(double maxHp, double baseAttack, double baseDefense, String name) {
        this(maxHp, baseAttack, baseDefense, name, CommonScripts.BASE_SCRIPTS, 0, 1, 0);
    }

    protected Entity() {
        this.position = new Position(-1, -1);
    }

    public double calcXpNeeded(){
        double xpNeeded = 50;
        for(int i = 0; i < level; i++){
            xpNeeded = xpNeeded*1.15;
        }
        return xpNeeded;
    }

    public void regenerate(int percentage){
        hp += getMaxHp() * percentage / 100;
    }
    public void lose(int percentage){
        takeDamage(getMaxHp() * percentage / 100);
    }

    public void increaseAtk(double amount){
        baseAttack += amount;
    }
    public void increaseAtk(int percentage){
        baseAttack += getBaseAttack() * percentage / 100;
    }

    public void decreaseAtk(double amount){
        baseAttack -= amount;
    }
    public void decreaseAtk(int percentage){
        baseAttack -= getBaseAttack() * percentage / 100;
    }

    public void increaseDef(double amount){
        baseDefense += amount;
    }
    public void increaseDef(int percentage){
        baseDefense += getBaseDefense() * percentage / 100;
    }

    public void decreaseDef(double amount){
        baseDefense -= amount;
    }

    public void decreaseDef(int percentage){
        baseDefense -= getBaseDefense() * percentage / 100;
    }

    public double getHpPercentage(){
        return getHp() / getMaxHp();
    }

    @JsonIgnore
    public Color getColor(){
        return getHp() > getMaxHp() * 0.6 ? Colors.SUCCESS : getHp() > getMaxHp() * 0.3 ? Colors.WARNING : Colors.ERROR.brighter();
    }
    @JsonIgnore
    public Color getDisplayColor(){
        return Colors.ERROR;
    }

    @JsonIgnore
    public List<Script> getScripts() {
        if (scripts == null && scriptTypes != null) {
            scripts = scriptTypes.stream()
                    .map(ScriptType::createScript)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else if (scripts != null && !scripts.getClass().getName().contains("ArrayList")) {
            scripts = new ArrayList<>(scripts);
        }
        return scripts;
    }
    public void setScripts(List<Script> scripts) {
        this.scripts = new ArrayList<>(scripts);
        updateScriptTypes();
    }
    private void updateScriptTypes() {
        if (scripts != null) {
            this.scriptTypes = scripts.stream()
                    .map(this::getScriptType)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    protected ScriptType getScriptType(Script script) {
        return switch (script.getClass().getSimpleName()) {
            case "Up" -> ScriptType.UP;
            case "Down" -> ScriptType.DOWN;
            case "Left" -> ScriptType.LEFT;
            case "Right" -> ScriptType.RIGHT;
            case "Jump" -> ScriptType.JUMP;
            case "BaseAttack" -> ScriptType.BASE_ATTACK;
            case "Help" -> ScriptType.HELP;
            case "ShowName" -> ScriptType.SHOW_NAME;
            case "Dash" -> ScriptType.DASH;
            case "Knockback" -> ScriptType.KNOCKBACK;
            case "Beam" -> ScriptType.BEAM;
            case "Explosion" -> ScriptType.EXPLOSION;
            case "HyperBeam" -> ScriptType.HYPER_BEAM;
            case "Regen" -> ScriptType.REGEN;
            case "AttackBoost" -> ScriptType.ATK_BOOST;
            case "DefenseBoost" -> ScriptType.DEF_BOOST;
            case "Rage" -> ScriptType.RAGE;
            default -> throw new IllegalArgumentException("Unknown script type: " + script.getClass().getSimpleName());
        };
    }

    public void takeDamage(double damage){
        hp -= Math.max((damage - baseDefense), 0);
    }

    @JsonIgnore
    public boolean isDead(){
        return hp <= 0;
    }

    public double toXp(){
        return maxHp + baseAttack*3 + baseDefense*4;
    }

    public void addScript(List<Script> tasks){
        this.getScripts().addAll(tasks);
        updateScriptTypes();
    }

    public void removeTask(Script task){
        getScripts().remove(task);
        updateScriptTypes();
    }

    private double checkValue(double val){
        return Math.clamp(val, 0, Integer.MAX_VALUE);
    }
}