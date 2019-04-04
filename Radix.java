import java.util.Arrays;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> list = new MyLinkedList<Integer>();
    for (int i = 0; i < 20; i++) buckets[i] = new MyLinkedList<Integer>();
    int max = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] > max) max = data[i];
      int item = data[i];
      int digit = Math.abs(item)%10;
      if (item < 0) buckets[9-digit].add(item);
      else buckets[digit+10].add(item);
    }
    //System.out.println(Arrays.deepToString(buckets));
    for (int i = 0; i < 20; i++) {
      list.extend(buckets[i]);
      buckets[i] = new MyLinkedList<Integer>();
    }
    int place = 10;
    while (place <= max) {
      //System.out.println("list: "+list);
      while (list.hasNext()) {
        int item = list.next();
        //System.out.println(item);
        int digit = (Math.abs(item)/place)%10;
        if (item < 0) buckets[9-digit].add(item);
        else buckets[digit+10].add(item);
      }
      //System.out.println(Arrays.deepToString(buckets));
      list = new MyLinkedList<Integer>();
      for (int i = 0; i < 20; i++) {
        list.extend(buckets[i]);
        buckets[i] = new MyLinkedList<Integer>();
      }
      place*=10;
      list.reset();
    }
    int i = 0;
    while (list.hasNext()) data[i++] = list.next();
  }

  public static void main(String[] args) {
    int[] data = new int[]{103, 45, -1, 241, 861, 359, 175, 920, 4};
    System.out.println("unsorted: "+Arrays.toString(data));
    radixsort(data);
    System.out.println("sorted: "+Arrays.toString(data));
  }
}
