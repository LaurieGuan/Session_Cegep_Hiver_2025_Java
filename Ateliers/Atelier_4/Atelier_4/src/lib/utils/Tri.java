package lib.utils;
import lib.Characters.Entity;
import java.util.ArrayList;

public class TriFouille {

    public static class sss {
        public static void sort(ArrayList<Entity> entites) {
            for (int i = 0; i < entites.size(); i++) {
                int max = i;
                for (int u = i + 1; entites.size() > u; u++) {
                    if (entites.get(u).initiative > entites.get(max).initiative) {
                        max = u;
                    }
                }
                Entity temp = entites.get(i);
                entites.set(i, entites.get(max));
                entites.set(max, temp);
            }
        }
    }

    public static class seperation {
        public static void sort(ArrayList<Entity> entities) {
            ArrayList<Entity> ennemis = new ArrayList<>();

            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i).friendOrFoe() == Modes.status.ENNEMY) {
                    ennemis.add(entities.remove(i));
                    i--;
                }

            }   for (Entity ennemi:ennemis) {
                entities.add(ennemi);
            }
        }
    }
}
