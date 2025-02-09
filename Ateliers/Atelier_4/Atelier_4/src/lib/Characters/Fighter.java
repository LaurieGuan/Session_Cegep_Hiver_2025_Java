package lib.Characters;
import lib.utils.Modes;
import lib.utils.Terminal;

public class Fighter extends Entity {

    public Fighter(String nom) {
        super(nom, Modes.status.FRIENDLY,2, 16, 10, 8);
    }

    public Fighter(String nom, int pointsAttaque, int pointsDefense, int pointsVie, int pointsDommages) {
        super(nom, Modes.status.FRIENDLY, pointsAttaque, pointsDefense, pointsVie, pointsDommages);
    }

    @Override
    public void coupSpecial(Entity ennemi) {
        coupSpecial(ennemi, Modes.descriptions.BASIC);
    }

    @Override public void coupSpecial(Entity ennemi, Modes.descriptions mode) {
        int dommagesEffectues = this.getDommage(Modes.getteurs.ALEATOIRE);
        ennemi.changerVie(-dommagesEffectues);

        switch (mode) {
            case Modes.descriptions.BASIC:
                System.out.printf("%sL'attaque réussie!%n" +
                                "%sVous avez effectué %d de dommages!%s%n%n",
                        Terminal.color.BLUE,
                        Terminal.color.RED, dommagesEffectues, Terminal.color.CLEAR);
                break;
            case Modes.descriptions.VERBOSE:
                System.out.printf("Vous envoyez un coup puissant vers %s.%n" +
                                "%sL'attaque touche l'adversaire!%n" +
                                "%sAvec la force du guerrier, vous lui effectuez %d de dommages!%s%n%n",
                        ennemi.nom,
                        Terminal.color.BLUE,
                        Terminal.color.RED, dommagesEffectues, Terminal.color.CLEAR);
                break;
            case Modes.descriptions.SILENT:
                System.out.printf("%sL'attaque réussie!%s%n%n",
                        Terminal.color.BLUE, Terminal.color.CLEAR);
        }
    }
}