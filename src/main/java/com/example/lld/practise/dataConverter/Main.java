package com.example.lld.practise.dataConverter;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        String text = "id,name,code,price,brand_name,category$1,iPhone 15,IP14PRO54,85000,APPLE,SMARTPHONE$2,iPhone 14,IP14PRO53,75000,APPLE,SMARTPHONE$3,S23,IP14PRO55,55000,SAMSUNG,SMARTPHONE";
        String text = "id,name,code,price,brand_name,category\n" +
                "1,iPhone 15,IP14PRO54,85000,APPLE,SMARTPHONE\n" +
                "2,iPhone 14,IP14PRO53,75000,APPLE,SMARTPHONE\n" +
                "3,S23,IP14PRO55,55000,SAMSUNG,SMARTPHONE";
        String columnSeparator = ",";
        String rowSeparator = "\n";

//        String rowSeparator = "\\$"; // -> as $ is special character
        String newText = "aaaaaaa\nbbbbbbb\nccccccc";

//        String[] splittedData = text.split("\n");
//        for (String s: splittedData) {
//            System.out.println(s);
//        }
//
//        String str = "geekss@for@geekss";
//        String[] arrOfStr = str.split("@");
//
//        for (String a : arrOfStr)
//            System.out.println(a);


        List<List<String>> result1 = convertData(text, rowSeparator, columnSeparator);
//        System.out.println(result1);

//        List<List<String>> result2 = sqlQuery(result1, Map.of("brand_name", "APPLE",
//                                "category", "SMARTPHONE",
//                "name", "iPhone 14"));

//        System.out.println(result2);
    }

    private static List<List<String>> convertData(String input, String rowSeparator, String columnSeparator) {
        List<List<String>> result = new ArrayList<>();
//        if (input == null || input.length() == 0
//                || rowSeparator.isBlank() || columnSeparator.isBlank()
//                || rowSeparator.length() > 1 || columnSeparator.length() > 1
//                || rowSeparator.equals(columnSeparator)) {
//            System.out.println("validation failed");
//            return result;
//        }

        String[] rowData = input.split(rowSeparator);
//        System.out.println(rowData);

        for (String row: rowData) {
            System.out.println(row);
            List<String> innerList = new ArrayList<>();
            String[] columnData = row.trim().split(columnSeparator);

            for (String data: columnData) {
                System.out.println(data);
                innerList.add(data.trim());
            }
            result.add(innerList);
        }

        return result;
    }

//    private static List<List<String>> convertData(String data, char columnSeparator, char rowSeparator) {
//        List<List<String>> resultData = new ArrayList<>();
//        if (data == null || data.length() == 0) {
//            return resultData;
//        }
//
//
//        List<String> rows = new ArrayList<>();
//
//        for (int i = 0; i < data.length(); i++) {
//            StringBuilder sb = new StringBuilder();
//            while (i < data.length() && data.charAt(i) != rowSeparator) {
//                sb.append(data.charAt(i));
//                i++;
//            }
//            rows.add(sb.toString().trim());
//        }
//
//        for (String row : rows) {
//            List<String> columnData = new ArrayList<>();
//
//            for (int i = 0; i < row.length(); i++) {
//                StringBuilder sb1 = new StringBuilder();
//                while (i < row.length() && row.charAt(i) != columnSeparator) {
//                    sb1.append(row.charAt(i));
//                    i++;
//                }
//                columnData.add(sb1.toString().trim());
//            }
//            resultData.add(columnData);
//        }
//
//        return resultData;
//    }


    // TC -> O(N * M)
    private static List<List<String>> sqlQuery(List<List<String>> inputData, Map<String, String> query) {
        List<List<String>> result = new ArrayList<>();

        List<String> header = new ArrayList<>();
        if (!inputData.isEmpty()) {
            header = inputData.get(0);
        }

        for (int i = 1; i < inputData.size(); i++) {
            int querySuccessCount = 0;
            for (int j = 0; j < inputData.get(i).size(); j++) {
                if (query.containsKey(header.get(j)) && query.get(header.get(j)).equals(inputData.get(i).get(j))) {
                    querySuccessCount++;
                }
            }
            if (querySuccessCount == query.size()) {
                result.add(inputData.get(i));
            }
        }

        return result;
    }
}
