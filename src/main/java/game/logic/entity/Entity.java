package game.logic.entity;

import game.graphic.shared.Colors;
import game.logic.board.Position;
import game.logic.tasks.Script;
import game.logic.tasks.standard.CommonTasks;
import lombok.Data;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Data
public class Entity {
    private String name;

    private double hp;
    private double maxHp;
    private double defense;
    private double baseAttack;
    private double baseDefense;

    private List<Script> scripts;

    private double xp;

    private Position position;

    public Entity(double maxHp, double baseAttack, double baseDefense, String name, List<Script> tasks, double xp) {
        this.maxHp = checkValue(maxHp);
        this.hp = checkValue(maxHp);
        this.baseAttack = checkValue(baseAttack);
        this.baseDefense = checkValue(baseDefense);
        this.name = name == null ? "No name" : name;
        this.scripts = tasks;
        this.position = new Position(-1, -1);
        this.xp = xp;
    }
    public Entity(double maxHp, double baseAttack, double baseDefense, String name, List<Script> tasks) {
        this(maxHp, baseAttack, baseDefense, name, tasks, 0);
    }

    public Entity(double maxHp, double baseAttack, double baseDefense, String name) {
        this(maxHp, baseAttack, baseDefense, name, CommonTasks.BASE_TASKS);
    }

    public Color getColor(){
        return Colors.ERROR;
    }

    public void takeDamage(double damage){
        hp -= (damage - defense);
    }

    public boolean isDead(){
        return hp <= 0;
    }

    public double toXp(){
        return maxHp/10 + baseAttack + baseDefense*2;
    }

    public void addTask(Script... tasks){
        this.scripts.addAll(Arrays.asList(tasks));
    }

    public void removeTask(Script task){
        scripts.remove(task);
    }

    private double checkValue(double val){
        return Math.clamp(val, 0, 1000000000);
    }
}
