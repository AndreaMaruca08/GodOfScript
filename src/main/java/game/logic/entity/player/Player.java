package game.logic.entity.player;

import game.logic.entity.Entity;
import game.logic.scripts.level_based.lvl10.Lvl10Scripts;
import game.logic.scripts.level_based.lvl15.Lvl15Scripts;
import game.logic.scripts.standard.CommonScripts;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player extends Entity {

    public Player() {
        super(30, 10, 2.5, "Player");
    }

    public Player(Player playerToSave) {
        super(
                playerToSave.maxHp,
                playerToSave.baseAttack,
                playerToSave.baseDefense,
                playerToSave.name,
                playerToSave.scripts,
                playerToSave.xp,
                playerToSave.level,
                playerToSave.points
        );
    }

    public Player(String name) {
        super(30, 10, 2.5, name);
    }

    @Override
    public Color getDisplayColor() {
        return this.getColor();
    }

    public void reset(){
        this.hp = maxHp;
    }

    public void gainXp(double xp){
        this.xp += xp;
        if(this.xp >= nextLevelXp){
            this.xp -= nextLevelXp;
            nextLevelXp = nextLevelXp * 1.1;
            levelUp();
            gainXp(0);
        }
        DataSaver.savePlayer(this);
    }

    private void levelUp(){
        level++;
        maxHp = maxHp*1.1;
        baseAttack = baseAttack*1.1;
        baseDefense = baseDefense*1.1;
        points++;
        hp = maxHp;
        discoverNewScripts();

    }

    private void discoverNewScripts(){
        if(level >= 10 && !hasScript(ScriptType.DASH)) {
            addScript(Lvl10Scripts.LVL10_SCRIPTS);
        }
        if(level >= 15 && !hasScript(ScriptType.BEAM)) {
            addScript(Lvl15Scripts.LVL15_SCRIPTS);
        }
    }

    private boolean hasScript(ScriptType type) {
        return getScripts().stream()
                .map(this::getScriptType)
                .anyMatch(t -> t == type);
    }

    public void upgradeStat(TypeOfUpgrade stat){
        if(points <= 0)
            return;
        stat.upgrade(this);
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