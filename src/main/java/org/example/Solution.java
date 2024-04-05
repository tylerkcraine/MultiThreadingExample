package org.example;

public class Solution {
    int num;
    int total;

    public Solution(int num, int total){
        this.num = num;
        this.total = total;
    }

    @Override
    public String toString() {
        return num + " " + total;
    }

    @Override
    public int hashCode() {
        return this.num;
    }
}
