package lib.utils;

import lib.Characters.Entity;

public class Terminal {

    public static class color {
        public static final String RED = "\033[31m";
        public static final String GREEN = "\033[32m";
        public static final String YELLOW = "\033[33m";
        public static final String BLUE = "\033[34m";
        public static final String CLEAR = "\033[0m";
    }

    public static class menu {
        public static void choisirAction(Entity joueur) {
            String messageSpecial = joueur.estEnVie() ?
                    Terminal.color.GREEN + "Faire votre coup spécial,":
                    Terminal.color.YELLOW + "Coup spécial indisponible,";
            messageSpecial += Terminal.color.CLEAR;

            System.out.printf("%sC'est au tour de %s.%s%n" +
                            "Que voulez-vous faire?%n" +
                            "1 -\tFaire une attaque standard%n" +
                            "2 -\t%s%n" +
                            "3 -\tFuir.%n",
                    Terminal.color.GREEN, joueur.nom, Terminal.color.CLEAR, messageSpecial);

        }
        public static class attaqueBasique {

            public static void reussie(Entity joueur, Entity ennemi,
                                       Modes.descriptions description, int deAttaque,
                                       int dommagesEffectues, int difficultee) {

                switch (description) {
                    case Modes.descriptions.BASIC:
                        System.out.printf("%sL'attaque réussie!%n" +
                                        "%sVous avez effectué %d de dommages.%s%n%n",
                                Terminal.color.BLUE,
                                Terminal.color.RED, dommagesEffectues, Terminal.color.CLEAR);
                        break;
                    case Modes.descriptions.VERBOSE:
                        System.out.printf("Il faut un lancer de %d ou plus pour toucher %s.%n" +
                                        "Le dé tombe... C'est un %s%d%s%n" +
                                        "%sCela touche l'adversaire!%n" +
                                        "%sVous effectuez %d de dommage à l'adversaire!%s%n%n",
                                difficultee, ennemi.nom,
                                Terminal.color.RED, deAttaque, Terminal.color.CLEAR,
                                Terminal.color.BLUE,
                                Terminal.color.RED, dommagesEffectues, Terminal.color.CLEAR);
                        break;
                    case Modes.descriptions.SILENT:
                        System.out.printf("%sL'attaque réussie!%s%n%n",
                                Terminal.color.BLUE, Terminal.color.CLEAR);
                        break;
                }
            }

            public static void rate(Entity ennemi, Modes.descriptions description,
                                    int deAttaque, int difficultee) {

                switch (description) {
                    case Modes.descriptions.BASIC:
                        System.out.printf("%sL'attaque échoue!%n" +
                                        "Vous n'effectuez aucuns dommages.%s%n%n",
                                Terminal.color.YELLOW, Terminal.color.CLEAR);
                        break;
                    case Modes.descriptions.VERBOSE:
                        System.out.printf("Il faut un dé d'au moins %d pour toucher %s.%n" +
                                "Le dé tombe... C'est un %s%d%s%n" +
                                "%sCela ne touche pas l'adversaire.%s%n%n",
                                difficultee, ennemi.nom,
                                Terminal.color.RED, deAttaque, Terminal.color.CLEAR,
                                Terminal.color.YELLOW, Terminal.color.YELLOW);
                        break;
                    case Modes.descriptions.SILENT:
                        System.out.printf("%sL'attaque échoue!%s%n%n",
                                Terminal.color.YELLOW, Terminal.color.CLEAR);
                        break;
                }
            }
        }
    }
}
