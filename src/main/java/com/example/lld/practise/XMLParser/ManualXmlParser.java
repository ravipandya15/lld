package com.example.lld.practise.XMLParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Manual XML Parser + N-ary Tree
 *
 * Supported:
 * 1. Opening tags       <company>
 * 2. Closing tags       </company>
 * 3. Self closing tags  <employee />
 * 4. Attributes         <employee id="101" name="Ravi">
 * 5. Text               <name>Ravi</name>
 * 6. Nested elements
 *
 * Not intended to implement the complete XML specification.
 */
public class ManualXmlParser {

    // ============================================================
    // N-ARY TREE NODE
    // ============================================================

    static class Node {

        String id;
        String tagName;
        String data;

        Map<String, String> attributes = new HashMap<>();

        Node parent;
        List<Node> children = new ArrayList<>();

        Node(String tagName) {
            this.id = UUID.randomUUID().toString();
            this.tagName = tagName;
        }

        void addChild(Node child) {
            child.parent = this;
            children.add(child);
        }
    }


    // ============================================================
    // TREE
    // ============================================================

    static class XmlTree {

        Node root;

        // Makes node lookup O(1) average
        Map<String, Node> nodeMap = new HashMap<>();


        // ========================================================
        // PARSE FILE
        // ========================================================

        public void parse(String filePath) throws IOException {

            StringBuilder xml = new StringBuilder();

            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader(filePath))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    xml.append(line);
                }
            }

            parseXml(xml.toString());
        }


        // ========================================================
        // MANUAL XML PARSER
        // ========================================================

        private void parseXml(String xml) {

            /*
             * Stack contains the current path in the XML.
             *
             * Example:
             *
             * <company>
             *     <department>
             *         <employee>
             *
             * Stack:
             *
             * employee
             * department
             * company
             */

            Deque<Node> stack = new ArrayDeque<>();

            int i = 0;

            while (i < xml.length()) {

                // ------------------------------------------------
                // Text
                // ------------------------------------------------

                if (xml.charAt(i) != '<') {

                    int tagStart = xml.indexOf('<', i);

                    if (tagStart == -1) {
                        break;
                    }

                    String text =
                            xml.substring(i, tagStart).trim();

                    if (!text.isEmpty() && !stack.isEmpty()) {

                        Node current = stack.peek();

                        current.data = text;
                    }

                    i = tagStart;
                    continue;
                }


                // ------------------------------------------------
                // Find >
                // ------------------------------------------------

                int tagEnd = findTagEnd(xml, i);

                if (tagEnd == -1) {
                    throw new IllegalArgumentException(
                            "Invalid XML: missing >"
                    );
                }

                String token =
                        xml.substring(i + 1, tagEnd).trim();


                // ------------------------------------------------
                // XML declaration
                // <?xml version="1.0"?>
                // ------------------------------------------------

                if (token.startsWith("?")) {

                    i = tagEnd + 1;
                    continue;
                }


                // ------------------------------------------------
                // Comment
                // <!-- comment -->
                // ------------------------------------------------

                if (token.startsWith("!--")) {

                    int commentEnd =
                            xml.indexOf("-->", i);

                    if (commentEnd == -1) {
                        throw new IllegalArgumentException(
                                "Invalid XML comment"
                        );
                    }

                    i = commentEnd + 3;
                    continue;
                }


                // ------------------------------------------------
                // Closing tag
                // </employee>
                // ------------------------------------------------

                if (token.startsWith("/")) {

                    String closingTag =
                            token.substring(1).trim();

                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Unexpected closing tag: "
                                        + closingTag
                        );
                    }

                    Node current = stack.pop();

                    if (!current.tagName.equals(closingTag)) {

                        throw new IllegalArgumentException(
                                "Mismatched tags. Expected </"
                                        + current.tagName
                                        + "> but found </"
                                        + closingTag
                                        + ">"
                        );
                    }

                    i = tagEnd + 1;
                    continue;
                }


                // ------------------------------------------------
                // Self closing tag
                // <employee />
                // ------------------------------------------------

                boolean selfClosing =
                        token.endsWith("/");

                if (selfClosing) {

                    token =
                            token.substring(
                                    0,
                                    token.length() - 1
                            ).trim();
                }


                // ------------------------------------------------
                // Opening tag
                // <employee id="1">
                // ------------------------------------------------

                Node node =
                        parseOpeningTag(token);


                // ------------------------------------------------
                // Add to tree
                // ------------------------------------------------

                if (root == null) {

                    root = node;

                } else {

                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Multiple root elements"
                        );
                    }

                    stack.peek().addChild(node);
                }


                // Add to lookup map
                nodeMap.put(node.id, node);


                // ------------------------------------------------
                // If not self closing, put on stack
                // ------------------------------------------------

                if (!selfClosing) {
                    stack.push(node);
                }

                i = tagEnd + 1;
            }


            // ----------------------------------------------------
            // If stack isn't empty, XML is malformed
            // ----------------------------------------------------

            if (!stack.isEmpty()) {

                throw new IllegalArgumentException(
                        "Invalid XML: unclosed tag <"
                                + stack.peek().tagName
                                + ">"
                );
            }
        }


        // ========================================================
        // FIND END OF TAG
        // ========================================================

        private int findTagEnd(String xml, int start) {

            boolean insideQuotes = false;
            char quote = 0;

            for (int i = start; i < xml.length(); i++) {

                char c = xml.charAt(i);

                if ((c == '"' || c == '\'') && !insideQuotes) {

                    insideQuotes = true;
                    quote = c;

                } else if (c == quote && insideQuotes) {

                    insideQuotes = false;

                } else if (c == '>' && !insideQuotes) {

                    return i;
                }
            }

            return -1;
        }


        // ========================================================
        // PARSE OPENING TAG
        // ========================================================

        private Node parseOpeningTag(String token) {

            /*
             * Example:
             *
             * employee id="101" name="Ravi"
             *
             * First token = employee
             *
             * Remaining:
             * id="101" name="Ravi"
             */

            int index = 0;

            // Skip spaces
            while (index < token.length()
                    && Character.isWhitespace(
                    token.charAt(index))) {

                index++;
            }


            // ----------------------------------------------------
            // Read tag name
            // ----------------------------------------------------

            int start = index;

            while (index < token.length()
                    && !Character.isWhitespace(
                    token.charAt(index))) {

                index++;
            }

            String tagName =
                    token.substring(start, index);


            Node node =
                    new Node(tagName);


            // ----------------------------------------------------
            // Read attributes
            // ----------------------------------------------------

            while (index < token.length()) {

                // Skip spaces
                while (index < token.length()
                        && Character.isWhitespace(
                        token.charAt(index))) {

                    index++;
                }

                if (index >= token.length()) {
                    break;
                }


                // Attribute name
                int attrStart = index;

                while (index < token.length()
                        && token.charAt(index) != '='
                        && !Character.isWhitespace(
                        token.charAt(index))) {

                    index++;
                }

                String attrName =
                        token.substring(
                                attrStart,
                                index
                        );


                // Skip spaces
                while (index < token.length()
                        && Character.isWhitespace(
                        token.charAt(index))) {

                    index++;
                }


                // Expect =
                if (index >= token.length()
                        || token.charAt(index) != '=') {

                    throw new IllegalArgumentException(
                            "Invalid attribute: "
                                    + attrName
                    );
                }

                index++;


                // Skip spaces
                while (index < token.length()
                        && Character.isWhitespace(
                        token.charAt(index))) {

                    index++;
                }


                // Expect quote
                if (index >= token.length()
                        || (token.charAt(index) != '"'
                        && token.charAt(index) != '\'')) {

                    throw new IllegalArgumentException(
                            "Attribute value must be quoted"
                    );
                }

                char quote =
                        token.charAt(index);

                index++;


                // Read attribute value
                int valueStart = index;

                while (index < token.length()
                        && token.charAt(index) != quote) {

                    index++;
                }

                if (index >= token.length()) {

                    throw new IllegalArgumentException(
                            "Unclosed attribute value"
                    );
                }

                String attrValue =
                        token.substring(
                                valueStart,
                                index
                        );

                index++;


                node.attributes.put(
                        attrName,
                        attrValue
                );
            }

            return node;
        }


        // ========================================================
        // ADD NODE
        // ========================================================

        public void addNode(
                String parentId,
                Node newNode) {

            Node parent =
                    nodeMap.get(parentId);

            if (parent == null) {
                throw new IllegalArgumentException(
                        "Parent not found"
                );
            }

            parent.addChild(newNode);

            nodeMap.put(
                    newNode.id,
                    newNode
            );
        }


        // ========================================================
        // UPDATE DATA
        // ========================================================

        public void updateData(
                String nodeId,
                String newData) {

            Node node =
                    nodeMap.get(nodeId);

            if (node == null) {
                throw new IllegalArgumentException(
                        "Node not found"
                );
            }

            node.data = newData;
        }


        // ========================================================
        // DELETE NODE
        // ========================================================

        public void deleteNode(String nodeId) {

            Node node =
                    nodeMap.get(nodeId);

            if (node == null) {
                throw new IllegalArgumentException(
                        "Node not found"
                );
            }

            if (node == root) {
                throw new IllegalArgumentException(
                        "Cannot delete root"
                );
            }

            node.parent.children.remove(node);

            removeFromMap(node);
        }


        private void removeFromMap(Node node) {

            nodeMap.remove(node.id);

            for (Node child : node.children) {
                removeFromMap(child);
            }
        }


        // ========================================================
        // PRINT TREE
        // ========================================================

        public void printTree() {

            printTree(root, 0);
        }


        private void printTree(
                Node node,
                int level) {

            if (node == null) {
                return;
            }

            System.out.print(
                    "  ".repeat(level)
                            + "<"
                            + node.tagName
            );


            // Attributes
            for (Map.Entry<String, String> entry :
                    node.attributes.entrySet()) {

                System.out.print(
                        " "
                                + entry.getKey()
                                + "=\""
                                + entry.getValue()
                                + "\""
                );
            }


            System.out.print(">");


            // Data
            if (node.data != null) {

                System.out.print(
                        " " + node.data
                );
            }


            System.out.println(
                    "   [id="
                            + node.id.substring(0, 8)
                            + "]"
            );


            // Children
            for (Node child :
                    node.children) {

                printTree(
                        child,
                        level + 1
                );
            }
        }
    }


    // ============================================================
    // MAIN
    // ============================================================

    public static void main(String[] args)
            throws Exception {

        XmlTree tree =
                new XmlTree();


        // --------------------------------------------
        // Parse
        // --------------------------------------------

        tree.parse("/Users/ravipandya/Projects/LowLevelDesign/src/Practise.XMLParser/input.xml");


        System.out.println(
                "========== INITIAL TREE =========="
        );

        tree.printTree();


        // --------------------------------------------
        // ADD
        // --------------------------------------------

        Node department =
                tree.root.children.get(0);

        Node newEmployee =
                new Node("employee");

        tree.addNode(
                department.id,
                newEmployee
        );

        Node newName =
                new Node("name");

        newName.data = "Alice";

        tree.addNode(
                newEmployee.id,
                newName
        );


        System.out.println(
                "\n========== AFTER ADD =========="
        );

        tree.printTree();


        // --------------------------------------------
        // UPDATE
        // --------------------------------------------

        tree.updateData(
                newName.id,
                "Alice Smith"
        );


        System.out.println(
                "\n========== AFTER UPDATE =========="
        );

        tree.printTree();


        // --------------------------------------------
        // DELETE
        // --------------------------------------------

        tree.deleteNode(
                newEmployee.id
        );


        System.out.println(
                "\n========== AFTER DELETE =========="
        );

        tree.printTree();
    }
}