import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String[] arr = new String[] {"1", "2", "3", "4", "5"};
        arr = list.toArray(arr);
    }
}
