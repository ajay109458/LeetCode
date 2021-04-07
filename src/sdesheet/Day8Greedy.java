package sdesheet;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day8Greedy {

    int maxMeetingsInRoom(Pair[] meetings) {

        List<Pair> result = new ArrayList<>();

        Arrays.sort(meetings, (p1, p2) -> Integer.compare(p1.y, p2.y));

        for (Pair meeting : meetings) {
            if (result.isEmpty()) {
                result.add(meeting);
            } else {
               if ( result.get(result.size()-1).y < meeting.x ) {
                   result.add(meeting);
               }
            }
        }

        return result.size();

    }

    int maxPlatformRequired(int[] arrivals, int[] departures) {
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int count = 0;

        int a = 0;
        int d = 0;

        int max = 0;

        while (a < arrivals.length && d < departures.length) {
            if (arrivals[a] < departures[d]) {
                count++;
                a++;
            } else if (arrivals[a] > departures[d]) {
                count--;
                d++;
            } else {
                a++;
                d++;
            }

            max = Math.max(count, max);
        }

        return count;
    }

    static class Job {
        private int id;
        private int deadline;
        private int profit;
    }

    //https://www.techiedelight.com/job-sequencing-problem-deadlines/
    public int printJobScheduling(List<Job> jobs, int t) {
        int n = jobs.size();

        Collections.sort(jobs, (j1, j2) -> Integer.compare(j2.profit, j1.profit));

        int deadline = 0;
        int maxProfit = 0;

        for(int i = 0; i < jobs.size(); i++) {

            Job currJob = jobs.get(i);

            if (deadline < currJob.deadline) {
                maxProfit += (currJob.profit);
                deadline++;
            } else {
                continue;
            }
        }

        return maxProfit;
    }

    public static int minCoinsGreedy(int[] coins, int amount) throws Exception {

        int i = coins.length -1;

        int count = 0;

        while(i >= 0 && amount > 0) {
            if (coins[i] < amount) {
                amount -= coins[i];
                count++;
            } else {
                i--;
            }
        }

        if (amount < 0) {
            throw new Exception("Not valid input");
        }

        return count;
    }

    private static class JobInfo {
        int id;
        int profit;
        int deadline;

        public JobInfo() {

        }

        public JobInfo(int id, int profit, int deadline) {
            this.id = id;
            this.profit = profit;
            this.deadline = deadline;
        }
    }

    private static void jobSequencingProblem(Job[] jobs, int time) {
        Arrays.sort(jobs, (j1, j2) -> Integer.compare(j1.profit, j2.profit));

        boolean[] isSlotAvailable = new boolean[jobs.length];
        int[] jobIds = new int[jobs.length];

        for(int i = 0; i < jobs.length; i++) {
            for(int j = Math.min(time-1, jobs[i].deadline-1); j >= 0; j--) {
                if (isSlotAvailable[j]) {
                    isSlotAvailable[j] = false;
                    jobIds[j] = jobs[i].id;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(jobIds));
    }

}
