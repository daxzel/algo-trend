package groovy.algo

class BinaryHeap {
    int size = 0
    int height = 0
    Element[] array = new Element[500]
    class Element {
        int priority
        Object content

        Element(int priority, Object content) {
            this.content = content
            this.priority = priority
        }
    }

    void add(int priority, Object content) {
        if (size == 0) {
            array[0] = new Element(priority, content)
            height = 1
            size = 1
        } else {
            int elementsCounts = 2 << (height - 1)
            Integer index
            for (int i = size; i < elementsCounts + size; i++) {
                if (array[i] == null) index = i
            }
            if (index == null) {
                size += 2 << (height - 1)
                height++
                index = size
            }
            array[index] = new Element(priority, content)
            siftUp(index, size, height)
        }
    }

    Object remove() {
        Object head = array[0].content

        int elementsCounts = 2 << (height - 1)
        Integer index = null
        for (int i = size; i < elementsCounts + size; i++) {
            if (array[i] != null) index = i
        }

        if (index != null) {
            array[0] = array[index]
            siftDown(0)
        }

        boolean findElement = false
        for (int i = size; i < elementsCounts + size; i++) {
            if (array[i] != null) findElement = true
        }
        if (!findElement) {
            size -= 2 << (height - 1)
            height--
        }
        head
    }

    void siftDown(int index) {
        Element current = array[index]
        int leftIndex = 2 * index + 1
        int rightIndex = 2 * index + 2
        Element left = array[leftIndex]
        Element right = array[rightIndex]

        if ((left == null) && (right == null)) {
            return
        }
        if (left == null) {
            if (right.priority > current.priority) {
                array[index] = right
                array[rightIndex] = current
            }
        } else {
            if (right == null) {
                if (left.priority > current.priority) {
                    array[index] = left
                    array[leftIndex] = current
                }
            } else {
                int largerIndex
                if (left.priority > right.priority) {
                    largerIndex = leftIndex
                } else {
                    largerIndex = rightIndex
                }
                Element larger = array[largerIndex]
                array[index] = larger
                array[largerIndex] = current
                siftDown(largerIndex)
            }
        }
    }

    void siftUp(int index, int size, int height) {
        int root = getRoot(index)
        if (root < 0) {
            return
        }
        height--
        size = 2 << (height - 1)
        Element current = array[index]
        Element head = array[root]
        if (head.priority < current.priority) {
            array[index] = head
            array[root] = current
            siftUp(root, size, height)
        }
    }

    static int getRoot(int index) {
        return (index - 1) / 2
    }

    @Override
    String toString() {
        final result = new StringBuilder()
        array.each {
            if (it != null) {
                result.append(it.content)
                result.append(" ")
            }
        }
        return result.toString()
    }
}

