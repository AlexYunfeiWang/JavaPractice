import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OnlineElection {
    class TopVotedCandidate {
        Map<Integer, Integer> voteMap;
        Map<Integer, Integer> leadMap;
        int leadPerson;
        int size;
        int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            voteMap = new HashMap<>();
            leadMap = new HashMap<>();
            leadPerson = -1;
            size = persons.length;
            this.times = times;

            for (int i = 0; i < size; ++i) {
                voteMap.put(persons[i], voteMap.getOrDefault(persons[i], 0)+1);
                if (i == 0 || voteMap.get(persons[i]) >= voteMap.get(leadPerson)) {
                    leadPerson = persons[i];
                }
                leadMap.put(times[i], leadPerson);
            }
        }

        public int q(int t) {
            int targetIndex = Arrays.binarySearch(times, t);
            if (targetIndex >= 0) {
                return leadMap.get(times[targetIndex]);
            }
            else {
                return leadMap.get(times[-targetIndex-1-1]);
            }
        }
    }

}
