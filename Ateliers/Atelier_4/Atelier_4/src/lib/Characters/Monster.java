package lib.Characters;

import lib.utils.Modes;
import lib.utils.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends Entity {

    public Monster(String nom) {
        super(nom, Modes.status.ENNEMY,
                new Random().nextInt(2, 8),
                new Random().nextInt(15, 20),
                new Random().nextInt(3, 10),
                new Random().nextInt(2, 5));
    }

    public Monster(String nom, Modes.status relation) {
        super(nom, relation,
                new Random().nextInt(2, 8),
                new Random().nextInt(15, 20),
                new Random().nextInt(3, 10),
                new Random().nextInt(2, 5));
    }

    public Monster(String nom, Modes.status relation,
                   int pointsAttaque, int pointsDefense,
                   int pointsVie, int dommages) {
        super(nom, relation, pointsAttaque, pointsDefense, pointsVie, dommages);
    }

    @Override
    public void jouerTour() {
        Entity joueur = Entity.listeEntites.get(new Random().nextInt(0,
                Entity.getNombreJoueurs() - 1));

        System.out.printf("%sC'est au tour de %s%n" +
                    "%s%s attaque %s.%n",
                Terminal.color.YELLOW, this.nom,
                Terminal.color.CLEAR, this.nom, joueur.nom);

        this.attaquer(joueur, Modes.descriptions.VERBOSE);
    }

    @Override
    public void coupSpecial(Entity ennemi) {
    }
    @Override
    public void coupSpecial(Entity ennemi, Modes.descriptions mode) {
    }
}
