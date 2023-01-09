package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test(){
        int[] list = {1,2,3,4,6,7,9,5,12};
        Arrays.stream(list).sorted(
        ).forEach(
                System.out::println
        );
    }
}
