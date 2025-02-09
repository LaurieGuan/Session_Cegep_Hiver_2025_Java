package lib.Characters;
import lib.des.Des;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import lib.utils.Terminal;
import lib.utils.Modes;
import lib.utils.Tri;

import java.util.Scanner;

public abstract class Entity {
    private static ArrayList<Entity> listeEntites = new ArrayList<>();
    private static int nombreEntites;
    private static int nombreMonstres;
    private static int nombreJoueurs;

    public String nom;
    public int initiative;
    private int pointsAttaque;
    private int pointsDefense;
    private int pointsVie;
    private int dommage;
    private boolean specialCharge = true;
    private int manchesAvantSpecial = 0;
    private Modes.status relation;


    public Entity(String nom, Modes.status relation, int pAttaque, int pDefense, int pVie, int Dommage) {
        this.nom = nom;
        this.relation = relation;
        this.pointsAttaque = pAttaque;
        this.pointsDefense = pDefense;
        this.pointsVie = pVie;
        this.dommage = Dommage;

        listeEntites.add(this);
        nombreEntites++;
        if (this.relation == Modes.status.ENNEMY) {
            nombreMonstres++;
        } else if (this.relation == Modes.status.FRIENDLY) {
            nombreJoueurs++;
        }
    }

    public static void lancerCombat() {
        lancerInitiative();
        Tri.sss.sort(listeEntites);
        Tri.separation.sort(listeEntites);
    }

    public static void jouerManche()
    {
        for (int i = 0; !listeEntites.isEmpty() && i < listeEntites.size(); i++) {
            listeEntites.get(i).jouerTour();
            if (!listeEntites.get(i).estEnVie()){
                listeEntites.remove(i);
                i--;
                nombreEntites--;
            }
        }
    }

    public void jouerTour() {
        Scanner stdin = new Scanner(System.in).useDelimiter("\n");
        String entree;

        if (this.estEnVie()) {
            do {

                Terminal.menu.choisirAction(this);
                entree = stdin.next();
            } while (!entree.equals("1") && !entree.equals("2") && !entree.equals("3"));

            switch (entree) {
                case "1":

            }
        }
        else {
            System.out.printf("%s est mort(e).%n",
                    this.nom);
        }
    }

    public static void lancerInitiative() {
        for (Entity entity : listeEntites) {
            entity.initiative = Des.D20.lancerDe();
        }
    }

    public void supprimerEntite() {
        listeEntites.remove(this);
        nombreEntites--;

        switch (this.relation) {
            case Modes.status.FRIENDLY -> nombreJoueurs--;
            case Modes.status.ENNEMY -> nombreMonstres--;
        }
    }

    public static int getNombreEntites() {
        return nombreEntites;
    }

    public static int getNombreMonstres() {
        return nombreMonstres;
    }

    public static int getNombreJoueurs() {
        return nombreJoueurs;
    }

    public static ArrayList<Entity> getListeEntites() {
        return listeEntites;
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

    public void attaquer(Entity ennemi) {
        attaquer(ennemi, Modes.descriptions.BASIC);
    }
    public void attaquer(Entity ennemi, Modes.descriptions mode) {
//        Les statistiques des classes étaient complètements décâlissantes,
//        donc j'ai flip l'attaque et la défense pour rendre ça plus difficile
        int difficultee = ennemi.getPointsDefense() - this.pointsAttaque;
        int deAttaque = Des.D20.lancerDe();

        if (deAttaque >= difficultee) {
            int dommagesEffectues = this.getDommage(Modes.getteurs.ALEATOIRE);
            ennemi.changerVie(-dommagesEffectues);

            Terminal.menu.attaqueBasique.reussie(this, ennemi, mode,
                    deAttaque, dommagesEffectues, difficultee);
        }
        else {
            Terminal.menu.attaqueBasique.rate(ennemi, mode,
                    deAttaque, difficultee);
        }
    }

    public abstract void coupSpecial(Entity ennemi);
    public abstract void coupSpecial(Entity ennemi, Modes.descriptions mode);

    public boolean estEnVie() {
        return this.pointsVie > 0;
    }

    public Modes.status friendOrFoe() {
        return this.relation;
    }
}