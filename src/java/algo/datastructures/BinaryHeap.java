package algo.datastructures;

/**
 * Created by Tsarevskiy
 * <p>
 * Average	 Worst Space	O(n)	 O(n) Search	O(n)	 O(n) Insert	O(1)	 O(log n) Delete	O(log n) O(log n) Peek	O(1)	 O(1)
 */
public class BinaryHeap {

    public void add(int priority, Object content) {
        if (size == 0) {
            array[0] = new Element(priority, content);
            height = 1;
            size = 1;
        } else {
            int elementsCounts = 2 << (height - 1);
            Integer index = null;
            for (int i = size; i < elementsCounts + size; i++) {
                if (array[i] == null) {
                    index = i;
                }
            }

            if (index == null) {
                size += 2 << (height - 1);
                height++;
                index = size;
            }

            array[index] = new Element(priority, content);
            siftUp(index, size, height);
        }

    }

    public Object remove() {
        Object head = array[0].getContent();

        int elementsCounts = 2 << (height - 1);
        Integer index = null;
        for (int i = size; i < elementsCounts + size; i++) {
            if (array[i] != null) {
                index = i;
            }
        }

        if (index != null) {
            array[0] = array[index];
            siftDown(0);
        }

        boolean findElement = false;
        for (int i = size; i < elementsCounts + size; i++) {
            if (array[i] != null) {
                findElement = true;
            }
        }

        if (!findElement) {
            size -= 2 << (height - 1);
            height = height--;
        }

        return head;
    }

    public void siftDown(int index) {
        Element current = array[index];
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        Element left = array[leftIndex];
        Element right = array[rightIndex];

        if ((left == null) && (right == null)) {
            return;

        }

        if (left == null) {
            if (right.getPriority() > current.getPriority()) {
                array[index] = right;
                array[rightIndex] = current;
            }

        } else {
            if (right == null) {
                if (left.getPriority() > current.getPriority()) {
                    array[index] = left;
                    array[leftIndex] = current;
                }

            } else {
                int largerIndex;
                if (left.getPriority() > right.getPriority()) {
                    largerIndex = leftIndex;
                } else {
                    largerIndex = rightIndex;
                }

                Element larger = array[largerIndex];
                array[index] = larger;
                array[largerIndex] = current;
                siftDown(largerIndex);
            }

        }

    }

    public void siftUp(int index, int size, int height) {
        int root = getRoot(index);
        if (root < 0) {
            return;

        }

        height--;
        size = 2 << (height - 1);
        Element current = array[index];
        Element head = array[root];
        if (head.getPriority() < current.getPriority()) {
            array[index] = head;
            array[root] = current;
            siftUp(root, size, height);
        }

    }

    public static int getRoot(int index) {
        return (index - 1) / 2;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (Element element : array) {
            result.append(element.getContent());
            result.append(" ");

        }
        return result.toString();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Element[] getArray() {
        return array;
    }

    public void setArray(Element[] array) {
        this.array = array;
    }

    private int size = 0;
    private int height = 0;
    private Element[] array = new Element[500];

    public class Element {

        public Element(int priority, Object content) {
            this.content = content;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        private int priority;
        private Object content;
    }
}
