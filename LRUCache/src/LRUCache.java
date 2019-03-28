import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int val;
    Node next;
    Node prev;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
public class LRUCache {
    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        map = new HashMap<>();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node item = map.get(key);
        item.prev.next = item.next;
        item.next.prev = item.prev;

        moveToTail(item);

        return item.val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        Node newNode = new Node(key, value);
        if (map.size() >= capacity) {
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        moveToTail(newNode);
        map.put(key, newNode);
    }

    private void moveToTail(Node node) {
        node.prev = tail.prev;
        tail.prev = node;

        node.next = tail;
        node.prev.next = node;
    }
}
