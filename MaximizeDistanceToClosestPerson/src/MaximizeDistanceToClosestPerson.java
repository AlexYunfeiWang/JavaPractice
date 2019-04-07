public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }
        //maximum distance to closest person
        int maxDistance = 0;

        //index for looping through seats
        int index = 0;

        int lastOccupied = index-1;

        for (; index < seats.length; ++index) {
            if (seats[index] == 1) {
                if (lastOccupied == -1) {
                    maxDistance = Math.max(maxDistance, index);
                }
                else {
                    maxDistance = Math.max(maxDistance, (index - lastOccupied)/2);
                }
                lastOccupied = index;
            }
        }

        maxDistance = Math.max(maxDistance, seats.length-1-lastOccupied);

        return maxDistance;
    }
}
