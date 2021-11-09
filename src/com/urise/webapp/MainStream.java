package com.urise.webapp;

import java.util.*;

import static java.util.stream.Collectors.partitioningBy;

public class MainStream {
    public static void main(String[] args) {
//        реализовать метод через стрим int minValue(int[] values).
//        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
//        составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
//        Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

        int arrayRandomLength = new Random().ints(1, 1, 11).findFirst().getAsInt();

//        https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java ->
        int[] arrayRandomNumbers = new Random().ints(arrayRandomLength, 1, 10)
                .boxed()
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println(Arrays.toString(arrayRandomNumbers));
        System.out.println(minValue(arrayRandomNumbers));

//        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить
//        все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение
//        в один стрим.

//        List<Integer> integers = new Random().ints(arrayRandomLength, 1, 100)
//                .boxed()
//                .collect(Collectors.toList());
        List<Integer> integers = new ArrayList<>(Arrays.asList(8, 9));
        System.out.println(integers);
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
//        https://ru.stackoverflow.com/questions/1005303/Массив-чисел-в-одно-число-через-stream ->
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (acc, x) -> 10 * acc + x);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream().collect(partitioningBy(x -> x % 2 == 0));
        return map.get(map.get(false).size() % 2 == 0);
    }
}

