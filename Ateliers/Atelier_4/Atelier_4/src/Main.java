import lib.Characters.Mage;
import lib.Characters.Cleric;
import lib.utils.Modes;

public class Main {
    public static void main(String[] args)
    {
        Mage pierre = new Mage("Pierre", 2, 20, 200, 2);
        Cleric rejean = new Cleric("Rejean");

        rejean.coupSpecial(pierre);
        rejean.coupSpecial(pierre, Modes.descriptions.BASIC);
        rejean.coupSpecial(pierre, Modes.descriptions.VERBOSE);
        rejean.coupSpecial(pierre, Modes.descriptions.SILENT);

//        System.out.printf("Pierre n'a plus que %d points de vie.%n",
//                pierre.getPointsVie());

    }
}