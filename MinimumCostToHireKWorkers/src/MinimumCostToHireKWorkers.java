import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double min = Double.MAX_VALUE;
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; ++i) {
            workers[i] = new double[]{(double)wage[i]/(double)quality[i], (double)quality[i]};
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        PriorityQueue<Double> pq = new PriorityQueue<>();

        double qSum = 0.0;

        for (double[] worker : workers) {
            //System.out.println("ratio: " + worker[0] + ", quality: " + worker[1]);
            qSum += worker[1];
            pq.offer(-worker[1]);

            if (pq.size() > K) {
                qSum += pq.poll();
            }
            if (pq.size() == K) {
                min = Math.min(min, qSum * worker[0]);
                //System.out.println(qSum * worker[0]);
            }
        }
        return min;
    }
}

/*
"1. Every worker in the paid group should be paid in the ratio of their
quality compared to other workers in the paid group."
So for any two workers in the paid group,
we have wage[i] : wage[j] = quality[i] : quality[j]
So we have wage[i] : quality[i] = wage[j] : quality[j]
We pay wage to every worker in the group with the same ratio compared
to his own quality.

"2. Every worker in the paid group must be paid at least their minimum
wage expectation."
For every worker, he has an expected ratio of wage compared to his quality.

So to minimize the total wage, we want a small ratio.
So we sort all workers with their expected ratio, and pick up K first worker.
Now we have a minimum possible ratio for K worker and we their total quality.

As we pick up next worker with bigger ratio, we increase the ratio for whole group.
Meanwhile we remove a worker with highest quality so that we keep K workers
in the group.
We calculate the current ratio * total quality = total wage for this group.

We redo the process and we can find the minimum total wage.
Because workers are sorted by ratio of wage/quality.
For every ratio, we find the minimum possible total quality of K workers.

Time Complexity
O(NlogN) for sort.
O(NlogK) for priority queue.

"Question: "However, it is possible that current worker has the highest quality,
so you removed his quality in the last step, which leads to the problem that you
are "using his ratio without him"."

Please keep two points in mind when coding :

Total Cost = Total Quality * Rate(W/Q) the Rate should be the highest among all
the workers we have. Otherwise, we will pay one of them lower than minimum wage.
We sort the array by Rate.
Suppose we have a worker and then we remove the worker from the priority queue
we have, yet we still use his rate to calculate the total price. Like the author
stated, it doesn't matter.

Before and after we remove the quality, the quality stays the same for above
case. And , most importantly, we have a higher rate. This is the key point.
The same quality has been used with a lower rate before. So the higher rate
won't affect the end result. It will be higher anyway.

 */
