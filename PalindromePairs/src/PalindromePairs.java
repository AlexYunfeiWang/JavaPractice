import java.util.*;
public class PalindromePairs {
    class TrieNode {
        int idx;
        String word;
        TrieNode[] children = new TrieNode[26];
    }
    TrieNode root;
    boolean reverse = false;
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = helper(words);

        for (int i = 0; i < words.length; ++i) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        reverse = true;

        res.addAll(helper(words));

        return res;
    }

    public List<List<Integer>> helper(String[] words) {
        root = new TrieNode();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < words.length; ++i) {
            insert(words[i], i);
        }

        for (int i = 0; i < words.length; ++i) {
            res.addAll(find(words[i],i));
        }

        return res;
    }

    public void insert(String word, int idx) {
        TrieNode pCrawl = root;
        char[] arr = word.toCharArray();

        for (int i = 0; i < arr.length; ++i) {
            char c = arr[i];
            if (pCrawl.children[c - 'a'] == null) {
                pCrawl.children[c - 'a'] = new TrieNode();
            }
            pCrawl = pCrawl.children[c - 'a'];
        }
        pCrawl.word = word;
        pCrawl.idx = idx;
    }

    public List<List<Integer>> find(String word, int idx) {
        TrieNode pCrawl = root;
        char[] arr = word.toCharArray();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        //check in case a word is "" and the other word is palindrome like "aba"
        if (pCrawl.word != null && pCrawl.idx != idx && isPalindrome(arr, 0, arr.length-1)) {
            res.add(reverse ? Arrays.asList(idx, pCrawl.idx) : Arrays.asList(pCrawl.idx, idx));
        }

        for (int i = arr.length-1; i >= 0; --i) {
            char c = arr[i];
            pCrawl = pCrawl.children[c - 'a'];
            if (pCrawl == null) {
                break;
            }
            // found a word, now check whether the remainder of str is palindrome
            //for example, we have "dc" as a word in trie, and our input is "babcd"
            if (pCrawl.word != null && pCrawl.idx != idx && isPalindrome(arr, 0, i-1)) {
                //System.out.println(pCrawl.word + ", " + word.substring(0,i) + ", " + i + ", " + isPalindrome(arr, 0, i-1));
                res.add(reverse ? Arrays.asList(idx, pCrawl.idx) : Arrays.asList(pCrawl.idx, idx));
            }
        }
        return res;
    }

    public boolean isPalindrome(char[] arr, int lo, int hi) {
        if (reverse && lo > hi) {
            //when being called from line 68, it could happen when lo == 0 and hi == -1
            //also to prevent duplicate
            //System.out.println("flag: " + String.valueOf(arr) + ", lo = " + lo + ", hi = " + hi);
            return false;
        }
        while(lo < hi) {
            if (arr[lo++] != arr[hi--]) {
                return false;
            }
        }
        return true;
    }
}
