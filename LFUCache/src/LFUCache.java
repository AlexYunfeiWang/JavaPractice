import java.util.*;
public class LFUCache {

    Map<Integer, Integer> vals; //actual list of elements
    Map<Integer, Integer> counts; //record element and its frequency
    Map<Integer, LinkedHashSet<Integer>> lists; //key is frequency, value is the elements that have this frequency
    int cap;
    int min = -1;
    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!vals.containsKey(key))
            return -1;
        //find the frequency of this element
        int count = counts.get(key);
        //increase the frequency in counts map
        counts.put(key, count+1);
        //since this element's frequency has change, update lists map
        lists.get(count).remove(key);
        //if the old frequency is the last frequency and there is no other elements having this frequency, update least frequency
        if(count==min && lists.get(count).size()==0)
            min++;
        //if lists doesn't have the updated frequency as a key, create one
        if(!lists.containsKey(count+1))
            lists.put(count+1, new LinkedHashSet<>());
        //add this element to the updated frequency in lists map
        lists.get(count+1).add(key);

        //so in this operation, only lists map and counts map need to be updated
        return vals.get(key);
    }

    public void put(int key, int value) {
        if(cap<=0)
            return;
        //if the key already exists, simply update the value, and use get() method to update frequency (since it's convenient)
        if(vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        //if exceeds the capacity, need to evict the least frequency element
        if(vals.size() >= cap) {
            //go to the min frequency entry in lists map, simply evict the first one in order
            int evict = lists.get(min).iterator().next();
            //update lists map
            lists.get(min).remove(evict);
            //update actual list
            vals.remove(evict);
            //also update counts map
            counts.remove(evict);
        }
        //put new element into actual list
        vals.put(key, value);
        //put new element and frequency 1 into counts map
        counts.put(key, 1);
        //now the least frequency is 1
        min = 1;
        //update lists map on corresponding frequency
        lists.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */