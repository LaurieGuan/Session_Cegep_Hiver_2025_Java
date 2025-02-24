import java.util.ArrayList;

public class CustomArrayList {
    private ArrayList<Integer> list;

    public CustomArrayList() {
        list = new ArrayList<Integer>();
    }

    public boolean add(int valeur) {
        return list.add(valeur);
    }

    public boolean add(int index, int valeur) {
        boolean returnValue;

        if (index < 0 || index > list.size()) {
            returnValue = false;
        }
        else {
            list.add(index, valeur);
            returnValue = true;
        }

        return returnValue;
    }

    public int size() {
        return list.size();
    }
}