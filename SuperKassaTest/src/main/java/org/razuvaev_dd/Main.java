package org.razuvaev_dd;

import java.util.Arrays;
import java.util.List;

/**
 * Main class
 */
public class Main {

    /**
     * Default input JSON
     */
    public static final String JSON_INPUT_FILE = "default.json";

    /**
     * Main method
     * @param args input string (program params)
     */
    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);

        // Help msg
        if (argsList.contains("-h") || argsList.contains("--help")) {
            printHelpMsg();
            return;
        }

        // JSON file
        String jsonInputFile = JSON_INPUT_FILE;

        // Input JSON file
        if (argsList.contains("--input-json")) {
            int idx = argsList.indexOf("--input-json");

            try {
                jsonInputFile = argsList.get(idx+1);

                //ToDo: Validation file JSON format
            } catch (ArrayIndexOutOfBoundsException e) {
                printHelpMsg();
                return;
            }
        }

        //ToDo: Processing JSON
        System.out.println("Processing " + jsonInputFile);
    }

    public static void printHelpMsg() {
        System.out.println("=== H E L P ===");
        System.out.println("\t-h, --help   \t- this message");
        System.out.println("\t--input-json \t- input json file like \"default.json\" ");
        System.out.println("==== E N D ====");
    }
}