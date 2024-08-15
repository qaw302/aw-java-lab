import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String[] arr = new String[] {"1", "2", "3", "4", "5"};
        arr = list.toArray(arr);

        ListIterator<String> iterator = list.listIterator();
        int index = iterator.nextIndex();
        int index2 = iterator.previousIndex();
        String s1 = iterator.next();
        int index3 = iterator.nextIndex();
        int index4 = iterator.previousIndex();
        iterator.remove();
        int index5 = iterator.nextIndex();
        int index6 = iterator.previousIndex();

        String s3 = iterator.next();
        iterator.add("p");
        String s2 = iterator.previous();
        iterator.remove();
    }
}
