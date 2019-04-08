import java.util.*;

public class WordAbbreviation {
    int len = 0;
    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict == null || dict.size() == 0) {
            return dict;
        }
        len = dict.size();
        String[] abbreviation = new String[len];
        int[] prefixLen = new int[len];
        Map<String, List<Integer>> abbrMap = new HashMap<>();

        createAbbreviations(dict, abbreviation, prefixLen, abbrMap);

        eliminateAbbrConflict(dict, abbreviation, prefixLen, abbrMap);

        return Arrays.asList(abbreviation);
    }

    private void createAbbreviations(List<String> dict, String[] abbreviation, int[] prefixLen, Map<String, List<Integer>> abbrMap) {
        for (int i = 0; i < len; ++i) {
            prefixLen[i] = 1;
            abbreviation[i] = makeAbbr(dict.get(i), prefixLen[i]);
            if (!abbrMap.containsKey(abbreviation[i])) {
                abbrMap.put(abbreviation[i], new ArrayList<>());
            }
            abbrMap.get(abbreviation[i]).add(i);
        }
    }

    private void eliminateAbbrConflict(List<String> dict, String[] abbreviation, int[] prefixLen, Map<String, List<Integer>> abbrMap) {
        for (int i = 0; i < len; ++i) {
            if (abbrMap.get(abbreviation[i]).size() > 1) {
                List<Integer> conflictList = abbrMap.get(abbreviation[i]);
                abbrMap.remove(abbreviation[i]);
                for (int index : conflictList) {
                    prefixLen[index]++;
                    abbreviation[index] = makeAbbr(dict.get(index), prefixLen[index]);
                    if (!abbrMap.containsKey(abbreviation[index])) {
                        abbrMap.put(abbreviation[index], new ArrayList<>());
                    }
                    abbrMap.get(abbreviation[index]).add(index);
                }
                i--;
            }
        }
    }

    private String makeAbbr(String str, int prefixLength) {
        if (prefixLength >= str.length()-2) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, prefixLength));
        sb.append(str.length() - 1 - prefixLength);
        sb.append(str.charAt(str.length()-1));

        return sb.toString();
    }
}
