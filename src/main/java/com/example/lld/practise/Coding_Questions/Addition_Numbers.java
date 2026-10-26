package com.example.lld.practise.Coding_Questions;

public class Addition_Numbers {

    // Asked in Zepto SDE-2 round.
    public static void main(String[] args) {
        String s1 = "123,456,789";
        String s2 = "12";
        String result = getSum(s1, s2);
        System.out.println("result is: " + result);
    }

    private static String getSum(String s1, String s2) {
        if (s1.isBlank() && s2.isBlank()) return "";
        if (s1.isBlank()) return s2;
        if (s2.isBlank()) return s1;
        StringBuilder sb = new StringBuilder();

        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;
        int sum = 0;

        while (i >= 0 || j >= 0) {
            sum = 0;
            if (i >= 0) {
                if (s1.charAt(i) == ',') {
                    sb.append(s1.charAt(i));
                    i--;
                    continue;
                }
                sum += Character.getNumericValue(s1.charAt(i));
                i--;
            }

            if (j >= 0) {
                sum += Character.getNumericValue(s2.charAt(j));
                j--;
            }

            if (carry > 0) {
                sum += carry;
            }

            sb.append(sum % 10);
            carry = sum / 10;
        }


        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}
