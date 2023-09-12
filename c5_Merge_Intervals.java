import java.util.*;

public class c5_Merge_Intervals {

    static class Interval {
        int start;
        int end;
         public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    };
    class Meeting {
  int start;
  int end;

  public Meeting(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

    //TODO Merge Intervals (medium)
    public List<Interval> merge(List<Interval> intervals) {

        // TODO: Write your code here
        Collections.sort(intervals, ((c1, c2)-> c1.start - c2.start));

        List<Interval> ans = new LinkedList<>();
        Iterator<Interval> intervalIterator = intervals.iterator();
        Interval interval = intervalIterator.next();
        int start = interval.start;
        int end = interval.end;
        //迭代器和比较器的使用
        /*
        * intervalIterator.next() 返回的是迭代器中的下一个元素。
        * 在首次调用 intervalIterator.next() 之前，迭代器指向第一个元素。
        * 当您第一次调用 intervalIterator.next() 时，
        * 会返回集合中的第一个元素，并且迭代器会自动向下移动以准备获取下一个元素。
        * */
        while (intervalIterator.hasNext()){
            Interval next = intervalIterator.next();
            if (next.start <= end)
                end = Math.max(end, next.end);
            else{
                ans.add(new Interval(start, end));
                interval = next;
                start = interval.start;
                end = interval.end;
            }
        }
        ans.add(new Interval(start, end));
        return ans;
    }
    //TODO Insert Interval (medium)
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

            if (intervals.size() < 2)
                return intervals;
            List<Interval> mergedIntervals = new ArrayList<>();
            intervals.add(newInterval);
            Collections.sort(intervals, ((o1, o2) -> o1.start - o2.start));

            Iterator<Interval> it = intervals.iterator();
            Interval interval = it.next();
            int start = interval.start;
            int end = interval.end;

            while (it.hasNext()){
                Interval next = it.next();
                if (next.start <= end){
                    end = Math.max(end, next.end);
                }else {
                    mergedIntervals.add(new Interval(start, end));
                    interval = next;
                    start = interval.start;
                    end = interval.end;
                }
            }
            mergedIntervals.add(new Interval(start, end));
            // TODO: Write your code here
            return mergedIntervals;
        }


    //TODO Intervals Intersection (medium)
    public static List<Interval> merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> result = new ArrayList<Interval>();
        int i = 0, j = 0;
        while ( i < arr1.length && j < arr2.length){
            if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end) || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)){
                result.add( new Interval( Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
            }

            if (arr1[i].end < arr2[j].end)
                i++;
            else
                j++;
        }
        // TODO: Write your code here
        return result;
    }

    //TODO Conflicting Appointments (medium)
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        // TODO: Write your code here
        Comparator<Interval> startCompare = (s1, s2)->s1.start-s2.start;
        Arrays.sort(intervals, startCompare);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i+1].start)
                return false;
        }
        return true;
    }

    //TODO Problem Challenge 1: Minimum Meeting Rooms (hard)
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        int minRooms = 0;
        Collections.sort(meetings, (a,b)->a.start-b.start);
        PriorityQueue<Meeting> minHeap = new PriorityQueue<>(meetings.size(), (a,b)->a.end-b.end);

        for (Meeting meeting: meetings){
            while (!minHeap.isEmpty() && minHeap.peek().end <= meeting.start){
                minHeap.poll();
            }
            minHeap.offer(meeting);
            minRooms = Math.max(minRooms, minHeap.size());
        }
        // TODO: Write your code here
        return minRooms;
    }

    //TODO Problem Challenge 2: Maximum CPU Load (hard)
    class Job {
  int start;
  int end;
  int cpuLoad;

  public Job(int start, int end, int cpuLoad) {
    this.start = start;
    this.end = end;
    this.cpuLoad = cpuLoad;
  }
};

    public static int findMaxCPULoad(List<Job> jobs) {
        int maxCPULoad = 0;
        int currentCPULoad = 0;
        // TODO: Write your code here
        Collections.sort(jobs, (a, b) -> a.start - b.start);
        PriorityQueue<Job> minQueue = new PriorityQueue<>(jobs.size(), (a, b) -> a.end - b.end);

        for (Job job: jobs){
            while (!minQueue.isEmpty() && minQueue.peek().end < job.start){
                Job past = minQueue.poll();
                currentCPULoad -= past.cpuLoad;
            }

            minQueue.offer(job);
            currentCPULoad += job.cpuLoad;
            maxCPULoad  = Math.max(maxCPULoad, currentCPULoad);
        }
        return maxCPULoad;
    }

    //TODO Problem Challenge 3: Employee Free Time (hard)
    static class EmployeeInterval {
        Interval interval; // interval representing employee's working hours
        int employeeIndex; // index of the list containing working hours of this employee
        int intervalIndex; // index of the interval in the employee list

        public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
            this.interval = interval;
            this.employeeIndex = employeeIndex;
            this.intervalIndex = intervalIndex;
        }
    };
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<EmployeeInterval> heap = new PriorityQueue<>((a,b)->a.interval.start-b.interval.start);

        for (int i = 0; i < schedule.size(); i++) {
            heap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
        }
        Interval previousInterval = heap.peek().interval;
        while (!heap.isEmpty()){
            EmployeeInterval heapTop = heap.poll();
            if (previousInterval.end < heapTop.interval.start){
                result.add(new Interval(previousInterval.end, heapTop.interval.start));
                previousInterval = heapTop.interval;
            }else {
                if (previousInterval.end < heapTop.interval.end)
                    previousInterval = heapTop.interval;
            }

            List<Interval> employeeSchedule = schedule.get(heapTop.employeeIndex);
            if (employeeSchedule.size() > heapTop.intervalIndex + 1){
                heap.offer(new EmployeeInterval(employeeSchedule.get(heapTop.intervalIndex + 1), heapTop.employeeIndex, heapTop.intervalIndex+1));
            }
        }

        // TODO: Write your code here
        return result;
    }
}

//    public String TOP_N_KEYS(int n) {
//
//
//        List<Map.Entry<String, Integer>> list = new ArrayList<>(numHashMap.entrySet());
//
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
//                int valueComparison = entry2.getValue().compareTo(entry1.getValue());
//
//                if (valueComparison == 0) {
//                    return entry1.getKey().compareTo(entry2.getKey());
//                }
//
//                return valueComparison;
//            }
//        });
//
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < Math.min(n, list.size()); i++) {
//            Map.Entry<String, Integer> entry = list.get(i);
//            result.append(entry.getKey()).append("(").append(entry.getValue()).append("), ");
//        }
//
//        if (result.length() > 0) {
//            result.setLength(result.length() - 2);
//        }
//        return result.toString();
//    }

