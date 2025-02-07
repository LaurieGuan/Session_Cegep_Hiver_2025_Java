package lib.Characters;
import lib.utils.Terminal;
import lib.utils.Modes;
import java.util.Random;

public class Mage extends Entity {

    public Mage(String nom) {
        super(nom, 8, 19, 5, 4);
    }

    public Mage(String nom, int pointsAttaque, int pointsDefense, int pointsVie, int pointsDommages) {
        super(nom, pointsAttaque, pointsDefense, pointsVie, pointsDommages);
    }

    @Override
    public void coupSpecial(Entity ennemi) {
        coupSpecial(ennemi, Modes.descriptions.BASIC);
    }

    @Override public void coupSpecial(Entity ennemi, Modes.descriptions mode) {
        int dommagesEffectues = new Random().nextInt(1, 4);
        ennemi.changerVie(-dommagesEffectues);

        switch (mode) {
            case Modes.descriptions.BASIC:
                System.out.printf("%sL'attaque réussie!%n" +
                                "%sVous avez effectué %d de dommages!%s%n%n",
                        Terminal.BLUE,
                        Terminal.RED, dommagesEffectues, Terminal.CLEAR);
                break;
            case Modes.descriptions.VERBOSE:
                System.out.printf("Vous envoyez une boule de feu vers %s%n" +
                                "%sL'attaque touche l'adversaire!%n" +
                                "%sAvec la magie du mage, vous lui effectuez %d de dommages!%s%n%n",
                        ennemi.nom,
                        Terminal.BLUE,
                        Terminal.RED, dommagesEffectues, Terminal.CLEAR);
                break;
            case Modes.descriptions.SILENT:
                System.out.printf("%sL'attaque réussie!%s%n%n",
                        Terminal.BLUE, Terminal.CLEAR);
        }
    }
}
