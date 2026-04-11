package game.logic.entity;

import game.graphic.shared.Colors;
import game.logic.tasks.Task;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Entity {
    private String name;

    private double hp;
    private double maxHp;
    private double defense;
    private double baseAttack;
    private double baseDefense;

    private List<Task> tasks;

    public Entity(double maxHp, double baseAttack, double baseDefense, String name, List<Task> tasks) {
        this.maxHp = checkValue(maxHp);
        this.hp = checkValue(maxHp);
        this.baseAttack = checkValue(baseAttack);
        this.baseDefense = checkValue(baseDefense);
        this.name = name == null ? "No name" : name;
        this.tasks = tasks;
    }
    public Entity(double maxHp, double baseAttack, double baseDefense, String name) {
        this(maxHp, baseAttack, baseDefense, name, new ArrayList<>());
    }

    public Color getColor(){
        return Colors.ERROR;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    private double checkValue(double val){
        return Math.clamp(val, 0, 1000000000);
    }
}
