import java.util.Arrays;

public class Heaters {
    /*
    1. find the nearest heater for each house, by comparing the next heater with the current heater
    2. update the max radius each time
    */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        return getMaxRadius(houses, heaters);
    }

    private int getMaxRadius(int[] houses, int[] heaters) {
        int houseIndex = 0;
        int heaterIndex = 0;
        int max = 0;

        while(houseIndex < houses.length) {
            while(heaterIndex < heaters.length-1
                    && houses[houseIndex] - heaters[heaterIndex] >= heaters[heaterIndex+1] - houses[houseIndex]) {
                heaterIndex++;
            }
            max = Math.max(max, Math.abs(houses[houseIndex] - heaters[heaterIndex]));
            houseIndex++;
        }
        return max;
    }
}
