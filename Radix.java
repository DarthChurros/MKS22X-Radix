import java.util.Arrays;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> list = new MyLinkedList<Integer>();
    for (int i = 0; i < data.length; i++) list.add(data[i]);
    for (int i = 0; i < 20; i++) buckets[i] = new MyLinkedList<Integer>();
    int place = 0;
    int maxDigits = 0;
    for (int i = 0; i < data.length; i++) {
      if (Math.log10(data[i]) > maxDigits) maxDigits = (int)Math.log10(data[i]);
    }
    while (place <= maxDigits) {
      while (list.size() > 0) {
        int item = list.removeFront();
        int digit = (Math.abs(item)/(int)Math.pow(10,place))%10;
        if (item < 0) buckets[9-digit].add(item);
        else buckets[digit+10].add(item);
      }
      //System.out.println(Arrays.deepToString(buckets));
      list = new MyLinkedList<Integer>();
      for (int i = 0; i < 20; i++) {
        list.extend(buckets[i]);
        buckets[i] = new MyLinkedList<Integer>();
      }
      place++;
      //System.out.println("list: "+list);
    }
    int i = 0;
    while (list.size() > 0) data[i++] = list.removeFront();
  }

  public static void main(String[] args) {
    int[] data = new int[]{103, 45, -1, 241, 861, 359, 175, 920, 4};
    System.out.println("unsorted: "+Arrays.toString(data));
    radixsort(data);
    System.out.println("sorted: "+Arrays.toString(data));
  }
}
