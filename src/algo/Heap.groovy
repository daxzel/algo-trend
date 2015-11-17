package algo

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

BinaryHeap heap = new BinaryHeap()

heap.add(232, "232")
heap.add(2, "2")
heap.add(23, "23")
heap.add(29932, "29932")
heap.add(23992, "23992")

print(heap.toString())

