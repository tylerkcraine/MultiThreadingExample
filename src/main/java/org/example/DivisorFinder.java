package org.example;

public class DivisorFinder {
    public static int findTotalDivs(int n) {
        int left;
        int count = 0;

        for (int i = n; i > 0; i--) {
            if (n % i == 0) {
                left = n/i;
                if (left > i) break;
                count += 2;
            }
        }

        return count;
    }
}
