public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> list = new MyLinkedList<Integer>();
    for (int i = 0; i < data.length; i++) list.add(data[i]);
    for (int i = 0; i < 20; i++) buckets[i] = new MyLinkedList<Integer>();
    int place = 0;
    while (buckets[9].size() + buckets[10].size() < data.length) {
      while (list.size() > 0) {
        int item = list.removeFront();
        int digit = (item/(int)Math.pow(10,place))%10;
        if (item < 0) buckets[9-digit].add(item);
        else buckets[digit+10].add(item);
      }
    }
    int i = 0;
    while (list.size() > 0) data[i++] = list.removeFront();
  }
}
