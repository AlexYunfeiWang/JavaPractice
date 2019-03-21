import java.util.*;
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class SerializeAndDeserializeNaryTree {
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        return String.join(",", list);
    }

    public void serializeHelper(Node root, List<String> list) {
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node child : root.children) {
            serializeHelper(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] ss = data.split("\\,");
        Queue<String> queue = new LinkedList<>(Arrays.asList(ss));
        return deserializeHelper(queue);
    }

    public Node deserializeHelper(Queue<String> queue) {
        Node root = new Node();
        root.val = Integer.valueOf(queue.poll());
        int size = Integer.valueOf(queue.poll());

        root.children = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            root.children.add(deserializeHelper(queue));
        }
        return root;
    }
}

