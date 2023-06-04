package com.tech.smartin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class SmartinApplication {
    private final static int CHARACTER_RANGE = 320;

    public static void main(String[] args) {

        SpringApplication.run(SmartinApplication.class, args);
        System.out.println(generate(26, "meted", "etem"));
        isAnagramCounting("cdd", "arc");
    }


    public static boolean isAnagramCounting(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        int[] count = new int[CHARACTER_RANGE];
        for (int i = 0; i < string1.length(); i++) {
            count[string1.charAt(i)]++;
            count[string2.charAt(i)]--;
        }
        for (int i = 0; i < CHARACTER_RANGE; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean generate(int series, String str1, String str2) {
        Set<Integer> set = new TreeSet<>();
        Map<Character, Integer> primeCharacterMap = new HashMap<>();
        List<Integer> primeNumbers = Stream.iterate(1, i -> ++i)
                .filter(i -> i % 2 != 0)
                .filter(i -> {
                    set.add(i);
                    return 0 == set.stream()
                            .filter(p -> p != 1)
                            .filter(p -> !Objects.equals(p, i))
                            .filter(p -> i % p == 0)
                            .count();
                })
                .limit(series)
                .collect(toList());
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for (int i = 0; i < alphabet.length; i++) {
            primeCharacterMap.put(alphabet[i], primeNumbers.get(i));
        }
        int strLen1 = 1;
        int strLen2 = 1;

        for (int j = 0; j < str1.length(); j++) {
            strLen1 = primeCharacterMap.get(str1.charAt(j)) * strLen1;
        }

        for (int k = 0; k < str2.length(); k++) {
            strLen2 = primeCharacterMap.get(str2.charAt(k)) * strLen2;
        }

        return strLen1 == strLen2;

    }
}
