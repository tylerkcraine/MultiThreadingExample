package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Solution> solutionsList = Collections.synchronizedList(new ArrayList<>());
        List<DivisorRunnable> runList = new ArrayList<>();

        //partitioning range based on systems available processors
        int count = 1;
        int end = 200000;
        int step = end / (Runtime.getRuntime().availableProcessors()-1);
        while(runList.size() < Runtime.getRuntime().availableProcessors()-1) {
            runList.add(new DivisorRunnable(count, count+step, solutionsList));
            count += step;
        }
        runList.getLast().end = end;

        System.out.println("Multithreading example");
        long startTime = System.currentTimeMillis();
        //starting threads
        ArrayList<Thread> threads = new ArrayList<>();
        for (DivisorRunnable i : runList) {
            threads.add(new Thread(i));
            threads.getLast().start();
        }

        //checking if threads have finished execution
        for (Thread i : threads) {
            try {
                i.join();
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        synchronized (solutionsList) {
            int max = 0;
            int total = 0;
            for (Solution i : solutionsList) {
                if (total < i.total) {
                    max = i.num;
                    total = i.total;
                }
            }
            System.out.println(max + " " + total);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time diff: " + (endTime - startTime));

        System.out.println("\nSingle thread example");
        startTime = System.currentTimeMillis();
        int num = 0;
        int total = 0;
        for(int i = 1; i < end+1; i++) {
            int result = DivisorFinder.findTotalDivs(i);
            if (result > total) {
                num = i;
                total = result;
            }
        }
        System.out.println(num + " " + total);
        endTime = System.currentTimeMillis();
        System.out.println("Time diff: " + (endTime - startTime));
    }
}