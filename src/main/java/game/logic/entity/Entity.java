package game.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.graphic.shared.Colors;
import game.logic.board.Position;
import game.logic.entity.data.ScriptType;
import game.logic.tasks.Script;
import game.logic.tasks.standard.CommonScripts;
import lombok.Data;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Entity {
    protected String name;

    protected double hp;
    protected double maxHp;
    protected double defense;
    protected double baseAttack;
    protected double baseDefense;

    @JsonIgnore
    protected List<Script> scripts;

    protected List<ScriptType> scriptTypes;

    protected double xp;
    protected double nextLevelXp;

    protected int points;

    protected double level;

    @JsonIgnore
    private Position position;

    public Entity(double maxHp, double baseAttack, double baseDefense, String name, List<Script> scripts, double xp, double level, int points) {
        this.maxHp = checkValue(maxHp);
        this.hp = checkValue(maxHp);
        this.baseAttack = checkValue(baseAttack);
        this.baseDefense = checkValue(baseDefense);
        this.name = name == null ? "No name" : name;
        this.scripts = scripts;
        this.position = new Position(-1, -1);
        this.level = level;
        this.points = points;
        this.nextLevelXp = calcXpNeeded();
        this.xp = xp;
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
            xpNeeded = xpNeeded*1.30;
        }
        return xpNeeded;
    }

    @JsonIgnore
    public Color getColor(){
        return Colors.ERROR;
    }

    @JsonIgnore
    public List<Script> getScripts() {
        if (scripts == null && scriptTypes != null) {
            scripts = scriptTypes.stream()
                    .map(ScriptType::createScript)
                    .collect(Collectors.toList());
        }
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
        updateScriptTypes();
    }

    private void updateScriptTypes() {
        if (scripts != null) {
            this.scriptTypes = scripts.stream()
                    .map(this::getScriptType)
                    .collect(Collectors.toList());
        }
    }

    private ScriptType getScriptType(Script script) {
        return switch (script.getClass().getSimpleName()) {
            case "Up" -> ScriptType.UP;
            case "Down" -> ScriptType.DOWN;
            case "Left" -> ScriptType.LEFT;
            case "Right" -> ScriptType.RIGHT;
            case "Jump" -> ScriptType.JUMP;
            case "BaseAttack" -> ScriptType.BASE_ATTACK;
            case "Help" -> ScriptType.HELP;
            case "ShowName" -> ScriptType.SHOW_NAME;
            default -> throw new IllegalArgumentException("Unknown script type: " + script.getClass().getSimpleName());
        };
    }

    public void takeDamage(double damage){
        hp -= (damage - defense);
    }

    @JsonIgnore
    public boolean isDead(){
        return hp <= 0;
    }

    public double toXp(){
        return maxHp + baseAttack + baseDefense*2;
    }

    public void addTask(Script... tasks){
        this.getScripts().addAll(Arrays.asList(tasks));
        updateScriptTypes();
    }

    public void removeTask(Script task){
        getScripts().remove(task);
        updateScriptTypes();
    }

    private double checkValue(double val){
        return Math.clamp(val, 0, 1000000000);
    }
}