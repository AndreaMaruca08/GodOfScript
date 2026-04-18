package game.logic.sound;

import core.utilities.SoundManager;

public class Sounds extends SoundManager {
    public static void attackSound(){

    }

    public static void scriptSuccessSound(){

    }

    public static void scriptOccupiedSound(){}

    public static void scriptWinSound(){}

    public static void scriptLoseSound(){

    }
    public static void scriptNotFoundSound(){

    }
    public static void scriptErrorSound(){

    }
    public static void clickSound(){
        play("click.wav");
    }
    public static void hyperBeamSound(){
        play("hyperBeam.wav");
    }
    public static void explosionSound(){
        play("explosion.wav");
    }
}
