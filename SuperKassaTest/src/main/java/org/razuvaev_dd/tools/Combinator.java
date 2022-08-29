package org.razuvaev_dd.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Combines strings and exports full combinations to JSON
 */
public class Combinator {

    /**
     * Logger
     */
    static Logger log = Logger.getLogger(Combinator.class.getName());

    /**
     * List of StringCombinations
     */
    private final List<StringCombination> combinationsOfStrings;

    public Combinator() {
        combinationsOfStrings = new ArrayList<>();
    }

    /**
     * Add StringCombination and generate new combinations in combinator
     * @param sc StringCombination
     */
    public void add(StringCombination sc) {
        // add combination
        combinationsOfStrings.add(sc);

        int length = combinationsOfStrings.size();

        // combine with combinations that exist
        for(int i = 0; i < length-1; i++) {
            StringCombination stringCombination = Tools.mergeCombination(sc, combinationsOfStrings.get(i));
            if(stringCombination != null)
                combinationsOfStrings.add(stringCombination);
        }
    }

    public void exportFullCombinationsToJSON(File jsonFile) throws IOException {
        log.info("Exporting json: "+jsonFile.getAbsolutePath());
        List<List<String>> fullCombinationsOfStrings = new ArrayList<>();

        for (StringCombination sc : combinationsOfStrings) {
            if (sc.isFull())
                    fullCombinationsOfStrings.add(sc.getStringList());
        }

        new ObjectMapper().writeValue(jsonFile, fullCombinationsOfStrings);
    }

    @Override
    public String toString() {
        return combinationsOfStrings.toString();
    }
}
