import java.util.Arrays;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        Arrays.sort(freq);

        int i = 25;

        while(i >= 0 && freq[i] == freq[25]) {
            i--;
        }

        return Math.max(tasks.length, (freq[25] - 1) * (n+1) + 25 - i);
    }
}
