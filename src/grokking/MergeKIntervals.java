package grokking;

import java.util.*;

public class MergeKIntervals {

    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int[][] merge(int[][] inputIntervals) {

        Interval[] intervals = new Interval[inputIntervals.length];
        for(int i = 0; i < inputIntervals.length; i++) {
            intervals[i] = new Interval(inputIntervals[i][0], inputIntervals[i][1]);
        }

        Interval[] result = mergeIntervals(intervals);
        int[][] res = new int[result.length][2];
        for(int i = 0; i < result.length; i++) {
            res[i][0] = result[i].start;
            res[i][1] = result[i].end;
        }

        return res;
    }

    public static Interval[] mergeIntervals(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

        Stack<Interval> stack = new Stack<>();

        for (Interval interval : intervals) {
            if (stack.isEmpty()) {
                stack.push(interval);
            } else {
                if (stack.peek().end >= interval.start) {
                    stack.peek().end = Math.max(stack.peek().end, interval.end);
                } else {
                    stack.push(interval);
                }
            }
        }

        return stack.toArray(new Interval[0]);
    }

    public static List<Interval> insertNewInterval(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        for(Interval interval : intervals) {
            if (interval.start < newInterval.start) {
                result.add(interval);
            } else {

            }
        }

        return result;
    }

    public static List<Interval> insertAnInterval(Interval[] intervals, Interval newInterval) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        List<Interval> results = new ArrayList<>();

        for(Interval interval : intervals) {
            if (interval.end <= newInterval.start) {
                results.add(interval);
            } else if (newInterval.end < interval.start) {
                results.add(newInterval);
                newInterval = interval;
            } else {
                newInterval.end = Math.max(interval.end, newInterval.end);
                newInterval.start = Math.min(interval.start, newInterval.start);
            }
        }

        return results;
    }

    public static List<Interval> insertInterval(Interval[] intervals, Interval newInterval) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        List<Interval> result = new ArrayList<>();

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (newInterval.end < interval.start) {
                result.add(newInterval);

                // what happen to the current interval
                newInterval = interval;
            } else if (newInterval.end > interval.start) {
                newInterval.end  = Math.max(newInterval.end, interval.end);
            } else {
                interval.end = Math.max(newInterval.end, interval.end);
            }
        }

        return result;

    }


    public static List<Interval> intersectionOfIntervals(Interval[] intervals1, Interval[] intervals2) {
        List<Interval> results = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < intervals1.length && j < intervals2.length) {

            Interval first = intervals1[i];
            Interval second = intervals2[j];

            if( ( first.start >= second.start && first.start <= second.end) ||
                    (second.start >= first.start && second.start <= second.end)) {
                results.add(new Interval(Math.max(first.start, second.start), Math.min(first.end, second.end)));
            }

            if (first.end < second.end) {
                i++;
            } else {
                j++;
            }
        }

        return results;
    }

    public static List<Interval> merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> list = new ArrayList<>();

        int i =0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {

            if (arr1[i].start >= arr2[j].start && arr1[i].start < arr2[j].end
            || arr2[j].start >= arr1[i].start && arr1[j].start < arr2[i].end ) {
                list.add(new Interval(Math.max(arr1[i].start, arr2[i].start), Math.min(arr1[i].end, arr2[j].end)));
            }

            if (arr1[i].end < arr2[j].end) {
                i++;
            } else {
                j++;
            }
        }

        return list;
    }

    public static boolean canAttendAllAppointments(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        for(int i = 0 ;  i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i+1].start)
                return false;
        }

        return true;
    }


    private static class Meeting {
        int start;
        int end;
    }

    public static int getMinimumMeetingRooms(List<Meeting> meetings) {
        Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

        PriorityQueue<Meeting> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));

        int minRooms = 0;

        for(Meeting meeting : meetings) {

            while(!pq.isEmpty() && pq.peek().end <= meeting.start)
                pq.poll();

            pq.offer(meeting);
            minRooms = Math.max(minRooms, pq.size());
        }

        return minRooms;
    }

    public static int findMinimumRooms(List<Meeting> meetings) {
        Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

        int minRooms = 0;

        PriorityQueue<Meeting> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));

        for(Meeting meeting : meetings) {

            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll();
            }

            minHeap.offer(meeting);

            minRooms = Math.max(minRooms, minHeap.size());
        }

        return minRooms;
    }

    private static class CPULoad {
        int start;
        int end;
        int load;

        public CPULoad(int start, int end, int load) {
            this.start = start;
            this.end = end;
            this.load = load;
        }
    }

    public static int computeCPULoad(List<CPULoad> loads) {
        Collections.sort(loads, (a, b) -> Integer.compare(a.start, b.start));

        PriorityQueue<CPULoad> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));

        int maxLoad = 0;
        int currLoad = 0;
        for(CPULoad load : loads) {

            while(!pq.isEmpty() && pq.peek().end <= load.start) {
                CPULoad rem = pq.remove();
                currLoad -= rem.load;
            }

            pq.add(load);
            currLoad += load.load;

            maxLoad = Math.max(maxLoad, currLoad);
        }

        return maxLoad;
    }

    public static int maxLoadOnMachine(List<CPULoad> loads) {
        Collections.sort(loads, (a, b) -> Integer.compare(a.start, b.start));

        PriorityQueue<CPULoad> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));

        int maxLoad = 0;

        int currLoad = 0;
        for(CPULoad load : loads) {
            while(!minHeap.isEmpty() && minHeap.peek().end < load.start) {
                CPULoad cLoad = minHeap.poll();
                currLoad -= cLoad.load;
            }

            minHeap.offer(load);
            currLoad += load.load;

            maxLoad = Math.max(currLoad, maxLoad);
        }

        return Math.max(maxLoad, currLoad);
    }



}
