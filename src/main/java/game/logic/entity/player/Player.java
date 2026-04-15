package game.logic.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.graphic.shared.Colors;
import game.logic.entity.Entity;
import game.logic.scripts.level_based.lvl10.Lvl10Scripts;
import game.logic.scripts.standard.CommonScripts;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Player extends Entity {

    public Player() {
        super(30, 10, 1.5, "Player");
    }

    public Player(String name) {
        super(60, 10, 1.5, name);
    }

    @Override
    @JsonIgnore
    public Color getColor(){
        return getHp() > getMaxHp() * 0.6 ? Colors.SUCCESS : getHp() > getMaxHp() * 0.3 ? Colors.WARNING : Colors.ERROR.brighter();
    }

    public void reset(){
        this.hp = maxHp;
    }

    public void gainXp(double xp){
        this.xp += xp;
        if(this.xp >= nextLevelXp){
            this.xp -= nextLevelXp;
            nextLevelXp = nextLevelXp * 1.30;
            levelUp();
            gainXp(0);
        }
    }

    private void levelUp(){
        level++;
        maxHp = maxHp*1.10;
        baseAttack = baseAttack*1.10;
        baseDefense = baseDefense*1.09;
        points++;
        hp = maxHp;
        discoverNewScripts();
        DataSaver.savePlayer(this);
    }

    private void discoverNewScripts(){
        if(level >= 10 && !hasScript(ScriptType.DASH)) {
            addScript(Lvl10Scripts.LVL10_SCRIPTS);
        }
    }

    private boolean hasScript(ScriptType type) {
        return getScripts().stream()
                .map(this::getScriptType)
                .anyMatch(t -> t == type);
    }

    public void upgrade(TypeOfUpgrade upgrade){
        if(points <= 0)
            return;
        upgrade.upgrade(this);
        DataSaver.savePlayer(this);
    }

    public void initializeScriptsAfterLoad() {
        if (scriptTypes != null && !scriptTypes.isEmpty()) {
            if (scripts == null || scripts.size() != scriptTypes.size()) {
                scripts = scriptTypes.stream()
                        .map(ScriptType::createScript)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if (scripts == null || scripts.isEmpty()) {
            setScripts(new ArrayList<>(CommonScripts.BASE_SCRIPTS));
        }
    }
}