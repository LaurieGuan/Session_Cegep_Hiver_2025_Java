import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    @org.junit.jupiter.api.Test
    void testAdd() {
        CustomArrayList array = new CustomArrayList();
        long lastValue = Runtime.getRuntime().totalMemory();
        assertEquals(true, array.add(2));
        assertEquals(true, array.add(1, 0));
        assertEquals(false, array.add(-1, 1));

        for (int i = array.size(); i < Integer.MAX_VALUE; i++) {

            if (lastValue >= 8518631420L) {
                try {
                    array.add(3);
                }
                catch (Exception e) {
                    System.out.println(i);
                }
            } else {
                array.add(3);
            }

            if (!(Runtime.getRuntime().totalMemory() == lastValue)) {
                lastValue = Runtime.getRuntime().totalMemory();
                System.out.println(lastValue);
            }
        }

        assertEquals(true, array.add(1));
    }
}