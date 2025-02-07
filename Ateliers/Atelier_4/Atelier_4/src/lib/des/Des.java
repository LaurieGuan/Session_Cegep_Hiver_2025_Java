package lib.des;
import java.util.Random;

public class Des {

    public static class D20 {
        public static int lancerDe() {
            return new Random().nextInt(1,20);
        }
    }

    public static class D12 {
        public static int lancerDe() {
            return new Random().nextInt(1, 12);
        }
    }

    public static class D10 {
        public static int lancerDe() {
            return new Random().nextInt(1, 10);
        }
    }

    public static class D8 {
        public static int lancerDe() {
            return new Random().nextInt(1, 8);
        }
    }

    public static class D6 {
        public static int lancerDe() {
            return new Random().nextInt(1, 6);
        }
    }

    public static class D4 {
        public static int lancerDe() {
            return new Random().nextInt(1, 4);
        }
    }
}
