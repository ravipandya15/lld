# Coding Question asked in Ebay
You're given a HTML/XML file with lot of markup tags. There could be nested tags as well. You're supposed to write a write a parsing function that would read the file and construct a n-ary tree.
Extension:
You will also add functionalities like:
addNode, deleteNode and updateData accordingly.

Interviewer told he doesn't need working code for this and only asked for approach.


## My answer

- Use Stack Data structure

````
    Class Node {
        int id;
        String tagName;
        List<Node> children = new ArrayList<>():
        Map<Integer, String> attributes = new HashMap<>();
        Node parent; -> Most important - this is used for backtracking
    }
````

- Check the first '<' (Opening parentheses) and compare it with Stack.top()'s first '<'
- Based on this decide, if currentNode is child of stack top node or at same level.