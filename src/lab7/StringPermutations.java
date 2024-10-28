package lab7;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutations {
    public static void main(String[] args) {
        String input = "aabc"; // Example input; you can change this to test other cases
        boolean removeDuplicates = true;

        // Recursive approach with timing
        long startRecursive = System.nanoTime();
        List<String> recursivePermutations = generatePermutationsRecursive(input, removeDuplicates);
        long endRecursive = System.nanoTime();
        System.out.println("Recursive Permutations: " + recursivePermutations);
        System.out.println("Recursive Time: " + (endRecursive - startRecursive) / 1e6 + " ms");

        // Non-recursive approach with timing
        long startNonRecursive = System.nanoTime();
        List<String> nonRecursivePermutations = generatePermutationsIterative(input, removeDuplicates);
        long endNonRecursive = System.nanoTime();
        System.out.println("Non-Recursive Permutations: " + nonRecursivePermutations);
        System.out.println("Non-Recursive Time: " + (endNonRecursive - startNonRecursive) / 1e6 + " ms");
    }

    // Recursive method to generate permutations
    private static List<String> generatePermutationsRecursive(String str, boolean removeDuplicates) {
        Set<String> resultSet = new HashSet<>();
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        permuteRecursive(str.toCharArray(), 0, removeDuplicates ? resultSet : result);

        if (removeDuplicates) {
            result.addAll(resultSet); // Add unique permutations only
        }
        return result;
    }

    private static void permuteRecursive(char[] chars, int index, Object result) {
        if (index == chars.length - 1) {
            if (result instanceof Set) {
                ((Set<String>) result).add(new String(chars)); // Add to set to handle duplicates
            } else {
                ((List<String>) result).add(new String(chars));
            }
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            permuteRecursive(chars, index + 1, result);
            swap(chars, i, index); // backtrack
        }
    }

    // Non-recursive method (iterative) to generate permutations
    private static List<String> generatePermutationsIterative(String str, boolean removeDuplicates) {
        List<String> result = new ArrayList<>();
        Set<String> resultSet = new HashSet<>();

        if (str == null || str.isEmpty()) {
            return result;
        }

        int n = str.length();
        int[] indexes = new int[n];
        char[] chars = str.toCharArray();
        if (removeDuplicates) {
            resultSet.add(new String(chars)); // Add first permutation
        } else {
            result.add(new String(chars));
        }

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(chars, i % 2 == 0 ? 0 : indexes[i], i);
                String permutation = new String(chars);
                if (removeDuplicates) {
                    resultSet.add(permutation);
                } else {
                    result.add(permutation);
                }
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        if (removeDuplicates) {
            result.addAll(resultSet);
        }
        return result;
    }

    // Helper method to swap characters in the array
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
