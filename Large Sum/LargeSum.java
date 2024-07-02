
import java.io.*;

public class LargeSum {

    /**
     * Adds two large numbers represented as strings.
     * The method processes each digit of the numbers, adds them together, 
     * and returns the full sum as a string.
     *
     * @param num1 The first number to add.
     * @param num2 The second number to add.
     * @return The full sum of the two numbers as a string.
     */
    public static String addLargeNumbers(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        boolean carry = false;
        while (i >= 0 || j >= 0) {
            char digit1 = (i >= 0) ? num1.charAt(i) : '0';
            char digit2 = (j >= 0) ? num2.charAt(j) : '0';
            int sum = Character.getNumericValue(digit1) + Character.getNumericValue(digit2);
            sum += carry ? 1 : 0;
            carry = sum >= 10;
            builder.append(sum % 10);
            i--;
            j--;
        }
        if (carry) {
            builder.append(1);
        }
        // remove trailing 0s
        while (builder.length() > 1 && builder.charAt(builder.length() - 1) == '0') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {

        //long startTime = System.currentTimeMillis();
        String inputFile = "./input.txt";
        String totalSum = "0";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalSum = addLargeNumbers(totalSum, line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input file", e);
        }

        String first10Digits = totalSum.length() > 10 ? totalSum.substring(0, 10) : totalSum;
        System.out.println("Full sum: " + totalSum);
        System.out.println("First 10 digits: " + first10Digits);
        //long endTime = System.currentTimeMillis();
        //System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}
