package game.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.graphic.shared.Colors;
import game.logic.entity.data.DataSaver;
import game.logic.tasks.standard.CommonScripts;

import java.awt.*;

public class Player extends Entity{

    public Player() {
        super(100, 10, 1, "PLAYER");
    }

    @Override
    @JsonIgnore
    public Color getColor(){
        return getHp() > getMaxHp() * 0.6 ? Colors.SUCCESS : getHp() > getMaxHp() * 0.3 ? Colors.WARNING : Colors.ERROR.brighter();
    }

    public void gainXp(double xp){
        this.xp += xp;
        if(this.xp >= nextLevelXp){
            this.xp = 0;
            nextLevelXp = nextLevelXp*1.30;
            levelUp();
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
        upgrade.upgrade(this);
        DataSaver.savePlayer(this);
    }

    public void initializeScriptsAfterLoad() {
        if (getScripts().isEmpty() && (scriptTypes == null || scriptTypes.isEmpty())) {
            setScripts(CommonScripts.BASE_SCRIPTS);
        }
    }
}