package lib.Characters;
import lib.des.Des;
import java.lang.Math;
import java.util.Random;
import lib.utils.Terminal;
import lib.utils.Modes;

public abstract class Entity {
    public String nom;
    private int pointsAttaque;
    private int pointsDefense;
    private int pointsVie;
    private int dommage;

    public Entity(String nom, int pAttaque, int pDefense, int pVie, int Dommage) {
        this.nom = nom;
        this.pointsAttaque = pAttaque;
        this.pointsDefense = pDefense;
        this.pointsVie = pVie;
        this.dommage = Dommage;
    }

    public int getPointsVie() {
        return this.pointsVie;
    }

    public int getPointsDefense() {
        return this.pointsDefense;
    }

    public int getPointsAttaque() {
        return this.pointsAttaque;
    }

    public void changerVie(int alteration) {
        if (alteration < 0 && Math.abs(alteration) > this.pointsVie) {
            this.pointsVie = 0;
        }
        else {
            this.pointsVie += alteration;
        }
    }

    public int getDommage() {
        return getDommage(Modes.getteurs.ATTRIBUT);
    }

    public int getDommage(Modes.getteurs mode) {
        int valeurRetour = 0;
        switch (mode){
            case Modes.getteurs.ATTRIBUT -> valeurRetour = this.dommage;
            case Modes.getteurs.ALEATOIRE -> valeurRetour = new Random().nextInt(1, this.dommage);
        }
        return valeurRetour;
    }

    public int getDommage(int min, int max) {
        return new Random().nextInt(min, max);
    }

    public void attaquer(Entity ennemi) {
        attaquer(ennemi, Modes.descriptions.BASIC);
    }
    public void attaquer(Entity ennemi, Modes.descriptions mode) {
        int difficultee = ennemi.getPointsDefense() - this.pointsAttaque;
        int deAttaque = Des.D20.lancerDe();

        if (deAttaque >= difficultee) {
            int dommagesEffectues = this.getDommage(Modes.getteurs.ALEATOIRE);
            ennemi.changerVie(-dommagesEffectues);

            switch (mode) {
                case Modes.descriptions.BASIC:
                    System.out.printf("%sL'attaque réussie!%n" +
                                    "%sVous avez effectué %d de dommages.%s%n%n",
                            Terminal.BLUE,
                            Terminal.RED, dommagesEffectues, Terminal.CLEAR);
                    break;
                case Modes.descriptions.VERBOSE:
                    System.out.printf("Il faut un lancer de %d ou plus pour toucher %s.%n" +
                                    "Le dé tombe... C'est un %s%d%s%n" +
                                    "%sCela touche l'adversaire!%n" +
                                    "%sVous effectuez %d de dommage à l'adversaire!%s%n%n",
                            difficultee, ennemi.nom,
                            Terminal.RED, deAttaque, Terminal.CLEAR,
                            Terminal.BLUE,
                            Terminal.RED, dommagesEffectues, Terminal.CLEAR);
                    break;
                case Modes.descriptions.SILENT:
                    System.out.printf("%sL'attaque réussie!%s%n%n",
                            Terminal.BLUE, Terminal.CLEAR);
                    break;
            }
            mourrir(ennemi);
        }
        else {
            switch (mode) {
                case Modes.descriptions.BASIC:
                    System.out.printf("%sL'attaque échoue!%n" +
                            "Vous n'effectuez aucuns dommages.%s%n%n",
                            Terminal.YELLOW, Terminal.CLEAR);
                    break;
                case Modes.descriptions.VERBOSE:
                    System.out.printf("Il faut un dé d'au moins %d pour toucher %s.%n" +
                            "Le dé tombe... C'est un %s%d%s%n" +
                            "%sCela ne touche pas l'adversaire.%s%n%n",
                            difficultee, ennemi.nom,
                            Terminal.RED, deAttaque, Terminal.CLEAR,
                            Terminal.YELLOW, Terminal.YELLOW);
                    break;
                case Modes.descriptions.SILENT:
                    System.out.printf("%sL'attaque échoue!%s%n%n",
                            Terminal.YELLOW, Terminal.CLEAR);
            }
        }
    }

    public abstract void coupSpecial(Entity ennemi);
    public abstract void coupSpecial(Entity ennemi, Modes.descriptions mode);

    public static void mourrir(Entity entity) {
        if (entity.getPointsVie() == 0) {
            System.out.printf("%s%s est mourru. :`(%s,%n",
                    Terminal.YELLOW, entity.nom, Terminal.CLEAR);
        }
    }
}