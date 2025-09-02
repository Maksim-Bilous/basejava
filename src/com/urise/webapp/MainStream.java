package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    static List<Integer> integers = new ArrayList<>();


    public static void main(String[] args) {

        System.out.println("[" + minValues(new int[]{1, 2, 3, 3, 2, 3}) + "]");

        integers.add(3);
        integers.add(2);
        integers.add(4);
        integers.add(5);

        System.out.println(OddOrEven(integers));
    }

    public static int minValues(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (res, d) -> res * 10 + d);
    }

    public static List<Integer> OddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        boolean isSumEven = sum % 2 == 0;

        return integers.stream().filter(x -> (isSumEven && x % 2 != 0) || (!isSumEven && x % 2 == 0)).collect(Collectors.toList());
    }


}
