import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * This is a program that randomly picks objects by their weight-based probabilities
 * User can choose different data types of objects and assign a weight on the object
 * 
 * The weight should be: w >= 0
 * 
 * if w is 0, then it means the corresponding object will not be randomly picked, the chance is 0%
 * 
 * put(object, weight) will insert/update the weight of a certain object
 * 
 * get() will randomly pick an object based on the proportional probability decided by weight
 * 
 * The sample size in the test cases is 100000, which can be changed by the user
 * 
 * 
 * Complexity: 
 * The put() complexity is O(N) average and O(N) worst case
 * The get() complexity is O(logN) average and O(logN) worst case 
 * 
 * Please kindly review the code and run sample test. Thank you! 
 * 
 * by Yunfei Wang
 * 
 */

public class Solution<T extends Object> {
	
	//this class Pair represents every entry(object, accumulative weight)
	class Pair{
		T obj;
		int accumulativeWeight;
		Pair(T obj, int accumulativeWeight) {
			this.obj = obj;
			this.accumulativeWeight = accumulativeWeight;
		}
	}
	
    List<Pair> pairList; //pairList is the main data structure, it's a list of pairs that we "put()"
    Random rand; //an instance of Random class
    int totalWeight; // current total(accumulative) weight
    
    Map<T, Integer> indexMap;//a hashmap whose key is an object, value is the index of the object in pairList
    Map<T, Integer> weightMap; //a hashmap whose key is an object, value is the weight of this object
    
    
    
    public Solution() {
    	//Initialize all the containers and variables
        rand = new Random();
        totalWeight = 0;
        indexMap = new HashMap<T, Integer>();
        weightMap = new HashMap<T, Integer>();
        pairList = new ArrayList<Pair>();
    }
    
    public void put(T obj, Integer w) {
    	//when we input an object, if the object doesn't exist
        if (!indexMap.containsKey(obj)) {
        	totalWeight += w; //we add up to the total weight
        	Pair newPair = new Pair(obj, totalWeight); //create the new Pair entry with the object and accumulative weight
        	pairList.add(newPair);//add new entry to the pairList
        	
        	indexMap.put(obj, pairList.size()-1); //record the new object and its index in pairList
        	weightMap.put(obj, w);//record the new object and its own weight
        }
        else {//if the object already exists, we only need to update the corresponding accumulative weights
        	int originalWeight = weightMap.get(obj);//first get the original weight of the object
        	int diff = w - originalWeight; //then calculate the difference between new weight and original weight
        	int startIndex = indexMap.get(obj); //we find out the location where we should start to update the accumulative weight
        	
        	//starting from startIndex, update all the following accumulative weights until the end of pairList
        	for (int i = startIndex; i < pairList.size(); ++i) {
        		pairList.get(i).accumulativeWeight += diff;
        	}
        	
        	//also need to update the totalWeight, since we will use it again when we call another "put()"
        	totalWeight = pairList.get(pairList.size()-1).accumulativeWeight;
        }
        
    }
    
    public T get() {
        
    	//we use a binary search approach
    	
        
        //first we randomly pick a number from 1 to the accumulative weight
        int randomPickWeight = rand.nextInt(pairList.get(pairList.size()-1).accumulativeWeight) + 1;
        
        //then we use a binary search approach to properly locate the object
        //by finding the first accumulative weight that is greater than the randomly picked weight
        
        //Once found, we return the object based on the index
        int start = 0;
        int end = pairList.size()-1;
        
        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if (pairList.get(mid).accumulativeWeight == randomPickWeight) {
                return pairList.get(mid).obj; 
                //if the randomly picked weight happens to be one of the accumulative weight, return directly
            }
            else if (pairList.get(mid).accumulativeWeight < randomPickWeight) { 
            	//if the randomly picked weight is greater than the current accumulative weight, update start
                start = mid;   
            }
            else {
            	//otherwise, update end
                end = mid;
            }
        }
        //we narrow down to 2 possibilities 
        //since the accumulative weight at "start" is smaller than the accumulative weight at "end", 
        //so we first check the one at "start"
        if (pairList.get(start).accumulativeWeight >= randomPickWeight) {
            return pairList.get(start).obj;
        }
        return pairList.get(end).obj;
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution<Character> s = new Solution<Character>();
        s.put('A', 10); 
        s.put('B', 20); 
        s.put('C', 30); 
        
        System.out.println("Run Test Case 1:");
        System.out.println("Current probability: \nA: 1/6\nB: 1/3\nC: 1/2");
        runTestCase(100000, s); //use sample size 100000 to run test case 1
        
        //set the weight of 'A' to 0, run test case 2
        s.put('A', 0);
        System.out.println("Run Test Case 2:");
        System.out.println("Current probability: \nA: 0\nB: 2/5\nC: 3/5");
        runTestCase(100000, s); //use sample size 100000 to run test case 2
        
        //update the weight of 'B', also add new entry ('D', 50)
        s.put('B', 100);
        s.put('D', 50);
        System.out.println("Run Test Case 3:");
        System.out.println("Current probability: \nA: 0\nB: 5/9\nC: 1/6\nD: 5/18");
        runTestCase(100000, s); //use sample size 100000 to run test case 2
	}
	
	public static void runTestCase(int sampleSize, Solution<Character> s) {
		
        Map<Character, Integer> testMap = new HashMap<>();
        
        for (int i = 0; i < sampleSize; ++i) {
        	char c = s.get();
        	testMap.put(c, testMap.getOrDefault(c,0)+1);
        }
        System.out.println("\nResult:");
        for (Character key : testMap.keySet()) {
        	System.out.println(key + "->" + testMap.get(key));
        }
        System.out.println("----------------------");
	}

}
