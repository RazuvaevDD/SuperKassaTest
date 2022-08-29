package org.razuvaev_dd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.razuvaev_dd.tools.Combinator;
import org.razuvaev_dd.tools.StringCombination;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


/**
 * Main class
 */
public class Main {

    /**
     * Logger
     */
    static Logger log = Logger.getLogger(Main.class.getName());

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

        // JSON file paths
        String jsonInputFile, jsonOutputFile;

        // Input JSON file
        if (argsList.contains("--input-json")) {
            int idx = argsList.indexOf("--input-json");

            try {
                jsonInputFile = argsList.get(idx+1);
            } catch (ArrayIndexOutOfBoundsException e) {
                printHelpMsg();
                return;
            }

            if(!jsonInputFile.toLowerCase().endsWith(".json")) {
                log.warning("Input file '" + jsonInputFile + "' has a name that does not end in .json ");
                printHelpMsg();
                return;
            }
        } else {
            log.warning("--input-json is missing!");
            printHelpMsg();
            return;
        }

        // Output JSON file
        if (argsList.contains("--output-json")) {
            int idx = argsList.indexOf("--output-json");

            try {
                jsonOutputFile = argsList.get(idx + 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                printHelpMsg();
                return;
            }

            if(!jsonOutputFile.toLowerCase().endsWith(".json")) {
                log.warning("Output file '" + jsonOutputFile + "' has a name that does not end in .json ");
                printHelpMsg();
                return;
            }
        } else {
            log.warning("--output-json is missing!");
            printHelpMsg();
            return;
        }

        log.info("Processing " + jsonInputFile + " ...");

        // List of arrays of strings
        List<List<String>> list;

        // Filling in the JSON data in the list
        try {
            list = new ObjectMapper()
                    .readValue(new File(jsonInputFile), new TypeReference<>() {});
        } catch (IOException e) {
            log.warning("File '" + jsonInputFile + "' is not founded or is not valid!");
            return;
        }

        // Create a combinator and fill it with the data collected in the previous step
        Combinator combinator = new Combinator();
        for(List<String> stringList : list) {
            // Interpret strings as special combination objects
            StringCombination stringCombination = new StringCombination(stringList);
            // and load them into the combinator
            combinator.add(stringCombination);
        }

        // Exporting full combinations from the combinator
        try {
            combinator.exportFullCombinationsToJSON(new File(jsonOutputFile));
        } catch (IOException e) {
            log.warning("The JSON file was not written! Do you have access to this path or does this path exist?");
        }
    }

    /**
     * Print help message
     */
    public static void printHelpMsg() {
        System.out.println("===================== H E L P =====================");
        System.out.println("\t-h, --help   \t- this message");
        System.out.println("\t--input-json \t- input json file like \"/home/user/input.json\" ");
        System.out.println("\t--output-json\t- output json like \"/home/user/result.json\"");
        System.out.println("\nExample: java -jar ./SuperKassaTest-1.0-SNAPSHOT-jar-with-dependencies.jar " +
                "--input-json /home/user/input.json --output-json /home/user/result.json");
        System.out.println("====================== E N D ======================");
    }
}
