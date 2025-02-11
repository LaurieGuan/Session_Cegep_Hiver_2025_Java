import lib.Characters.*;
import lib.utils.Modes;
import lib.utils.Tri;


public class Main {
    public static void main(String[] args)
    {
        Mage pierre = new Mage("Pierre");
        Entity rejean = new Fighter("Rejean");
        Entity guetan = new Cleric("Guetan");
        Monster gruk = new Monster("Gruk");
        Monster knoush = new Monster("Knoush");
        Monster Darkrim = new Monster("Darkrim", Modes.status.ENNEMY);

        Entity.lancerCombat();
        while (Entity.getNombreJoueurs() > 0 && Entity.getNombreMonstres() > 0) {
            Entity.jouerManche();
        }

    }
}