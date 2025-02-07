package lib.Characters;
import lib.utils.Terminal;
import lib.utils.Modes;

public class Cleric extends Entity {

    public Cleric(String nom) {
        super(nom, 17, 4, 8, 6);
    }

    public Cleric(String nom, int pointsAttaque, int pointsDefense, int pointsVie, int pointsDommages) {
        super(nom, pointsAttaque, pointsDefense, pointsVie, pointsDommages);
    }

    @Override
    public void coupSpecial(Entity allie) {
        coupSpecial(allie, Modes.descriptions.BASIC);
    }

    @Override public void coupSpecial(Entity allie, Modes.descriptions mode) {
        int vieRestauree = this.getDommage(2, 6);
        allie.changerVie(-vieRestauree);

        switch (mode) {
            case Modes.descriptions.BASIC:
                System.out.printf("%sVous soignez %s.%n" +
                                "%sVous avez effectué %d de soins!%s%n%n",
                        Terminal.BLUE, allie.nom,
                        Terminal.GREEN, vieRestauree, Terminal.CLEAR);
                break;
            case Modes.descriptions.VERBOSE:
                System.out.printf("%s a l'air blessé. Vous poser votre main sur sa blessure.%n" +
                                "%sVos pouvoirs de guérisons font fermer les plaies.%n" +
                                "%sAvec la main du guérisseur, vous lui redonnez %d de vie!%s%n%n",
                        allie.nom,
                        Terminal.BLUE,
                        Terminal.GREEN, vieRestauree, Terminal.CLEAR);
                break;
            case Modes.descriptions.SILENT:
                System.out.printf("%sVous le soignez!%s%n%n",
                        Terminal.BLUE, Terminal.CLEAR);
        }
    }
}
