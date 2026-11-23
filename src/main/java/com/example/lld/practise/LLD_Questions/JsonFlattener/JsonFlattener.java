package com.example.lld.practise.LLD_Questions.JsonFlattener;
import java.util.*;

public class JsonFlattener {

    /*
    Input format
    {
      "name": "Ravi",
      "age": 28,
      "address": {
        "city": "Surat",
        "state": "Gujarat",
        "country": "India"
      },
      "company": {
        "name": "Microsoft",
        "department": {
          "name": "Engineering",
          "team": "Copilot"
        }
      }
    }

    Expected Output

    {
      "name": "Ravi",
      "age": 28,
      "address.city": "Surat",
      "address.state": "Gujarat",
      "address.country": "India",
      "company.name": "Microsoft",
      "company.department.name": "Engineering",
      "company.department.team": "Copilot"
    }

     */
    public static Map<String, Object> flatten(
            Map<String, Object> input) {

        Map<String, Object> result = new LinkedHashMap<>();

        flattenHelper(input, "", result);

        return result;
    }

    private static void flattenHelper(
            Map<String, Object> input,
            String prefix,
            Map<String, Object> result) {

        for (Map.Entry<String, Object> entry : input.entrySet()) {

            String key = prefix.isEmpty()
                    ? entry.getKey()
                    : prefix + "." + entry.getKey();

            Object value = entry.getValue();

            if (value instanceof Map<?, ?>) {

                flattenHelper(
                        (Map<String, Object>) value,
                        key,
                        result
                );

            } else {
                result.put(key, value);
            }
        }
    }

    public static void main(String[] args) {

        // Address object
        Map<String, Object> address = new LinkedHashMap<>();
        address.put("city", "Surat");
        address.put("state", "Gujarat");
        address.put("country", "India");

        // Department object
        Map<String, Object> department = new LinkedHashMap<>();
        department.put("name", "Engineering");
        department.put("team", "Copilot");

        // Company object
        Map<String, Object> company = new LinkedHashMap<>();
        company.put("name", "Microsoft");
        company.put("department", department);

        // Root JSON object
        Map<String, Object> input = new LinkedHashMap<>();
        input.put("name", "Ravi");
        input.put("age", 28);
        input.put("address", address);
        input.put("company", company);

        // Flatten JSON
        Map<String, Object> result = flatten(input);

        // Print flattened key-value pairs
        System.out.println("Flattened JSON:");

        result.forEach((key, value) ->
                System.out.println(key + " = " + value)
        );
    }
}

