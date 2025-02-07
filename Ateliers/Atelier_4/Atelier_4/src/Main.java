import lib.utils.Modes;
import lib.Characters.Fighter;
import lib.Characters.Mage;
import lib.Characters.Cleric;
import lib.Characters.Entity;


public class Main {
    public static void main(String[] args)
    {
        Mage pierre = new Mage("Pierre");
        Entity rejean = new Fighter("Rejean");


        while (Entity.getNombreEntites() > 0) {
            Entity.jouerManche();
        }

    }
}