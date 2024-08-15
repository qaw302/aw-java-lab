import list.ArrayList;
import list.LinkedList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // MyList라는 이름의 사용자 정의 List 클래스 인스턴스 생성
        List<String> myList = new LinkedList<>();

        // List에 요소 추가
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Cherry");

        // 예상 결과와 실제 결과 비교
        boolean sizeTest = myList.size() == 3;
        System.out.println("Test size == 3: " + sizeTest);  // true

        // List의 요소 비교
        boolean elementsTest = myList.get(0).equals("Apple") &&
                myList.get(1).equals("Banana") &&
                myList.get(2).equals("Cherry");
        System.out.println("Test elements == [Apple, Banana, Cherry]: " + elementsTest);  // true

        // 특정 요소 제거
        myList.remove("Banana");

        // 요소 제거 후 다시 테스트
        boolean removeTest = myList.size() == 2 &&
                myList.get(0).equals("Apple") &&
                myList.get(1).equals("Cherry");
        System.out.println("Test after removing 'Banana': " + removeTest);  // true

        // List의 첫 번째 요소 가져오기
        boolean firstElementTest = myList.get(0).equals("Apple");
        System.out.println("Test first element == 'Apple': " + firstElementTest);  // true

        // List 비우기
        myList.clear();
        boolean clearTest = myList.size() == 0;
        System.out.println("Test size == 0 after clearing: " + clearTest);  // true

    }
}
