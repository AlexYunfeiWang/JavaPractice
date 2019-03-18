import java.util.*;
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        Set<String> dead = new HashSet<>();
        for (String s : deadends) {
            dead.add(s);
        }
        String start = "0000";
        if (dead.contains(start)) {
            return -1;
        }

        int count = 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        set.add(start);

        while(!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                for (int k = 0; k < 4; ++k) {
                    String next = cur.substring(0,k) + (char)((cur.charAt(k) - '0' - 1 + 10) % 10 + '0') + cur.substring(k+1);
                    if (target.equals(next)) {
                        return count;
                    }
                    if (!set.contains(next) && !dead.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }

                    next = cur.substring(0,k) + (char)((cur.charAt(k) - '0' + 1 + 10) % 10 + '0') + cur.substring(k+1);
                    if (target.equals(next)) {
                        return count;
                    }
                    if (!set.contains(next) && !dead.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
