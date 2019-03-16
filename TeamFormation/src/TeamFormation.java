import java.util.*;
public class TeamFormation {

    public static int formingTeams4(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {

            int cur = nums[i];
            int curSize = 0;
            PriorityQueue<Integer> pq = map.get(cur-1);
            if (pq != null) {
                Iterator<Integer> it = pq.iterator();
                while(it.hasNext()) {
                    curSize = pq.peek();
                    pq.poll();
                }
            }

            curSize++;
            if (!map.containsKey(cur)) {
                map.put(cur, new PriorityQueue<Integer>());
            }
            map.get(cur).add(curSize);
            printMap(map);
        }
        int ans = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            if (map.get(key).size() > 0) {
                ans = Math.min(ans, map.get(key).peek());
            }
        }
        return ans;
    }

    public static void printMap(Map<Integer, PriorityQueue<Integer>> map) {

        for (int key : map.keySet()) {
            System.out.print("key = " + key + " ----->  ");
            PriorityQueue<Integer> cur = map.get(key);
            Iterator<Integer> it = cur.iterator();
            while(it.hasNext()) {
                System.out.print(it.next() + ",");
            }
            System.out.println();
        }
        System.out.println("\n-----------------");
    }

    public static int formingTeams3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] cnt = new int[2*n];
        int t = 0;
        int cur = 0;
        for(int i = 0, j; i < n; i = j) {
            for(j = i + 1; j < n && nums[i] == nums[j]; j++) ;
            if(i > 0 && nums[i-1] + 1 != nums[j-1]) {
                System.out.println("flag");
                t = cur;
            }
            System.out.println("i = " + i);
            System.out.println("j = " + j);
            System.out.println("t = " + t);
            System.out.println("cur = " + cur);
            int len = Math.min(cur - t, j - i);
            for(int k = 0; k < len; k++) {
                cnt[cur-1-k]++;
            }
            printCnt(cnt);
            t = cur - len;
            for(int k = len; k < j - i; k++) {
                cnt[cur++]++;
            }
            printCnt(cnt);
            System.out.println("-----------------");
        }
        int ans = n;
        for(int i = 0; i < cur; i++) ans = Math.min(ans, cnt[i]);
        return ans;
    }

    public static void printCnt(int[] cnt) {
        System.out.println("print cnt: ");
        for (int i = 0; i < cnt.length; ++i) {
            System.out.print(cnt[i] + ",");
        }
        System.out.println();
    }

    public static Comparator<HashSet<Integer>> comp = new Comparator<HashSet<Integer>>() {
        @Override
        public int compare(HashSet<Integer> o1, HashSet<Integer> o2) {
            return o1.size() - o2.size();
        }
    };

    public static int formingTeams2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        PriorityQueue<HashSet<Integer>> pq = new PriorityQueue<HashSet<Integer>>(comp);
        Arrays.sort(nums);
        Map<Integer, Integer> setSizeMap = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int cur = nums[i];
            boolean found = false;
            Iterator<HashSet<Integer>> it = pq.iterator();

            while(it.hasNext()) {
                HashSet<Integer> curSet = it.next();
                if (!curSet.contains(cur) && (curSet.contains(cur+1) || curSet.contains(cur-1))) {
                    curSet.add(cur);
                    it.remove();
                    pq.add(curSet);
                    found = true;
                    break;
                }
            }
            if (!found) {
                HashSet<Integer> set = new HashSet<>();
                set.add(cur);
                pq.add(set);
                setSizeMap.put(pq.size()-1, set.size());
            }
        }
        int min = pq.peek().size();
        System.out.println("-------------------");
        return min;
    }

    public static int formingTeams(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<HashSet<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> setSizeMap = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int cur = nums[i];
            int smallest = Integer.MAX_VALUE;
            int targetSetIndex = -1;
            for (int j = 0; j < list.size(); ++j) {
                HashSet<Integer> set = list.get(j);
                if (!set.contains(cur) && (set.contains(cur+1) || set.contains(cur-1))) {
                    if (set.size() < smallest) {
                        smallest = set.size();
                        targetSetIndex = j;
                    }
                }
            }
            if (targetSetIndex != -1) {
                list.get(targetSetIndex).add(cur);
            }
            else {
                HashSet<Integer> set = new HashSet<>();
                set.add(cur);
                list.add(set);
                setSizeMap.put(list.size()-1, set.size());
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); ++i) {
            print(list.get(i));
            min = Math.min(list.get(i).size(), min);
        }
        System.out.println("-------------------");
        return min;
    }

    public static void print(HashSet<Integer> set) {
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{4,5,2,3,-4,-3,-5};
        int[] input2 = new int[]{-4};
        int[] input3 = new int[]{3,2,3,1};
        int[] input4 = new int[]{1,-2,-3,-4,-4,2,0,-1};
        //System.out.println(formingTeams4(input1));
        //System.out.println(formingTeams4(input2));
        //System.out.println(formingTeams4(input3));
        System.out.println(formingTeams4(input4));
    }
}
