package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;
import java.lang.IllegalArgumentException;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) throws IllegalArgumentException{        
        if(x == null || y == null) 
            throw new IllegalArgumentException("Una de las listas es nula");

        int n = x.size(), m = y.size(), i = 0, j = 0;
        
        while (i < n && j < m) {
            if (x.get(i) == y.get(j))
                i++;
            j++;
        }
        
        return i == n;
    }
}
