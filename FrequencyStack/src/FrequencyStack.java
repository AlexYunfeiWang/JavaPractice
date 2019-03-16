import java.util.*;
class Node {
    int num;
    Node next;
    Node prev;
    Node(int num) {
        this.num = num;
        next = null;
        prev = null;
    }
}
public class FrequencyStack {

    Node head;
    Node tail;
    int max;
    boolean maxExist;
    Map<Integer, Integer> map;

    public FrequencyStack() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        max = -1;
        maxExist = false;
        map = new HashMap<>();
    }

    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0)+1);
        if (max == -1 || (map.containsKey(max) && map.get(max) <= map.get(x))) {
            max = x;
            if (map.containsKey(max) && map.get(max) <= map.get(x)) {
                maxExist = true;
            }
        }

        Node newNode = new Node(x);

        //insert node in normal order
        newNode.next = tail;
        tail.prev.next = newNode;

        newNode.prev = tail.prev;
        tail.prev = newNode;

        System.out.println("current max = " + max);
        System.out.println("----------------");
    }

    public int pop() {
        printList();
        if (maxExist) {
            Node cur = tail.prev;
            while(cur != head) {
                if (map.get(cur.num) == map.get(max)) {
                    Node result = cur;
                    map.put(result.num, map.get(result.num)-1);

                    //delete target cur
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;

                    updateMax();
                    return result.num;
                }
                else {
                    cur = cur.prev;
                }
            }

        }
        else {
            Node result = tail.prev;
            map.put(result.num, map.get(result.num)-1);
            //printList();
            updateMax();

            int res = result.num;
            result.prev.next = tail;
            tail.prev = result.prev;
            return res;
        }
        return -1;
    }

    public void updateMax() {
        Node cur = tail.prev;
        while(cur != head) {
            if (map.get(max) < map.get(cur.num)) {
                max = cur.num;
                maxExist = true;
            }
            cur = cur.prev;
        }

        //System.out.println("max after update: " + max);
        //System.out.println("maxExist after update: " + maxExist);
    }

    private void printList() {
        Node cur = head.next;
        while(cur != tail) {
            System.out.print(cur.num + ", ");
            cur = cur.next;
        }
        System.out.println();
    }

    private void printMap() {
        for (int key : map.keySet()) {
            System.out.println(key + "->" + map.get(key));
        }
    }

    public static void main(String[] args) {
        FrequencyStack stack = new FrequencyStack();
        stack.push(1);
        stack.push(2);

        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(4);
        stack.push(5);


        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());

        stack.push(1);
        System.out.println("pop(): " + stack.pop());

    }
}
