package game.logic.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.graphic.shared.Colors;
import game.logic.entity.Entity;
import game.logic.scripts.standard.CommonScripts;

import java.awt.*;

public class Player extends Entity {

    public Player() {
        super(60, 10, 1.5, "Player");
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
        baseDefense = baseDefense*1.05;
        points++;
        hp = maxHp;
        DataSaver.savePlayer(this);
    }

    public void upgrade(TypeOfUpgrade upgrade){
        if(points <= 0)
            return;
        upgrade.upgrade(this);
        DataSaver.savePlayer(this);
    }

    public void initializeScriptsAfterLoad() {
        if (getScripts().isEmpty() && (scriptTypes == null || scriptTypes.isEmpty())) {
            setScripts(CommonScripts.BASE_SCRIPTS);
        }
    }
}