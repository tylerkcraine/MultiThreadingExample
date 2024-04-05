package org.example;

import java.util.List;

public class DivisorRunnable implements Runnable{

    int start;
    int end;
    List<Solution> storage;

    public DivisorRunnable(int start, int end, List<Solution> storage) {
        this.start = start;
        this.end = end;
        this.storage = storage;
    }


    @Override
    public void run() {
        int num = 0;
        int total = 0;
        for(int i = start; i < end; i++) {
            int result = DivisorFinder.findTotalDivs(i);
            if (result > total) {
                num = i;
                total = result;
            }
        }
        storage.add(new Solution(num,total));
    }

    @Override
    public String toString() {
        return start + " " + end;
    }
}
