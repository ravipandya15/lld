package com.example.lld.practise.LLD_Questions;

import java.util.TreeMap;

public class TreeMapMethods {

    /**
     * All has O(LogN) TC
     * Remember:
     * - floorEntry = closest previous or equal entry.
     * - ceilingEntry = closest next or equal entry.
     * - lowerEntry = closest strictly previous entry.
     * - higherEntry = closest strictly next entry.
     *
     *
     * Method	        Meaning	Example                 (key = 30)
     * ceilingEntry()	Greater than or equal	        30
     * floorEntry()	    Less than or equal	            30
     * higherEntry()	Strictly greater	            40
     * lowerEntry()	    Strictly smaller	            20
     * ceilingKey()	    Ceiling key only	            30
     * floorKey()	    Floor key only	                30
     * higherKey()	    Next greater key	            40
     * lowerKey()	    Previous smaller key	        20
     * firstEntry()	    Smallest entry	                10
     * lastEntry()	    Largest entry	                50
     * pollFirstEntry()	Retrieve and remove smallest	10
     * pollLastEntry()	Retrieve and remove largest	    50
     * tailMap()	    Greater than or equal	        30, 40, 50
     * headMap()	    Less than	                    10, 20
     * subMap()	        Range of keys	                20, 30

     */
    static void main() {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(10, "A");
        map.put(20, "B");
        map.put(30, "C");
        map.put(40, "D");
        map.put(50, "E");

        System.out.println("Original TreeMap: " + map);

        // 1. ceilingEntry(): Key >= given key
        System.out.println("\n--- ceilingEntry ---");
        System.out.println(map.ceilingEntry(25)); // 30=C
        System.out.println(map.ceilingEntry(30)); // 30=C
        System.out.println(map.ceilingEntry(60)); // null

        // 2. floorEntry(): Key <= given key
        System.out.println("\n--- floorEntry ---");
        System.out.println(map.floorEntry(25)); // 20=B
        System.out.println(map.floorEntry(30)); // 30=C
        System.out.println(map.floorEntry(5));  // null

        // 3. higherEntry(): Key > given key
        System.out.println("\n--- higherEntry ---");
        System.out.println(map.higherEntry(30)); // 40=D

        // 4. lowerEntry(): Key < given key
        System.out.println("\n--- lowerEntry ---");
        System.out.println(map.lowerEntry(30)); // 20=B

        // 5. ceilingKey()
        System.out.println("\n--- ceilingKey ---");
        System.out.println(map.ceilingKey(25)); // 30

        // 6. floorKey()
        System.out.println("\n--- floorKey ---");
        System.out.println(map.floorKey(25)); // 20

        // 7. higherKey()
        System.out.println("\n--- higherKey ---");
        System.out.println(map.higherKey(30)); // 40

        // 8. lowerKey()
        System.out.println("\n--- lowerKey ---");
        System.out.println(map.lowerKey(30)); // 20

        // 9. tailMap(): Keys >= given key
        System.out.println("\n--- tailMap ---");
        System.out.println(map.tailMap(30));
        // {30=C, 40=D, 50=E}

        System.out.println(map.tailMap(30, false));
        // {40=D, 50=E}

        // 10. headMap(): Keys < given key
        System.out.println("\n--- headMap ---");
        System.out.println(map.headMap(30));
        // {10=A, 20=B}

        System.out.println(map.headMap(30, true));
        // {10=A, 20=B, 30=C}

        // 11. subMap(): Range of keys
        System.out.println("\n--- subMap ---");
        System.out.println(map.subMap(20, 40));
        // {20=B, 30=C}

        System.out.println(map.subMap(20, true, 40, true));
        // {20=B, 30=C, 40=D}

        // 12. firstEntry() and lastEntry()
        System.out.println("\n--- First and Last ---");
        System.out.println(map.firstEntry()); // 10=A
        System.out.println(map.lastEntry());  // 50=E

        // 13. pollFirstEntry() and pollLastEntry()
        System.out.println("\n--- Poll First and Last ---");
        System.out.println(map.pollFirstEntry()); // 10=A
        System.out.println(map.pollLastEntry());  // 50=E
        System.out.println(map); // {20=B, 30=C, 40=D}

        // 14. Descending order
        System.out.println("\n--- Descending Map ---");
        System.out.println(map.descendingMap());

        // 15. Descending keys
        System.out.println("\n--- Descending Keys ---");
        System.out.println(map.descendingKeySet());

        // 16. First and last keys
        System.out.println("\n--- First and Last Keys ---");
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());

        // 17. Remove by key
        System.out.println("\n--- Remove ---");
        map.remove(30);
        System.out.println(map);
    }
}
