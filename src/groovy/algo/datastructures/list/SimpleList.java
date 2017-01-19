package algo.datastructures.list;

/**
 * Created by andrey tsarevskiy
 */
public class SimpleList {
    public static class SimpleListNode {
        public SimpleListNode next;
        public Integer data;

        public SimpleListNode(Integer data) {
            this.data = data;
        }

        public void print() {
            System.out.print('[');
            System.out.print(data);
            SimpleListNode temp = this.next;
            while (temp != null) {
                System.out.print("," + temp.data);
                temp = temp.next;
            }
            System.out.print(']');
        }
    }
}
