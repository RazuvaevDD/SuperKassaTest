package org.razuvaev_dd.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * External tools
 */
public class Tools {

    /**
     * Merge StringCombinations with generation new StringCombination
     * @param stringCombination1 stringCombination 1
     * @param stringCombination2 stringCombination 2
     * @return new StringCombination or null
     */
    public static StringCombination mergeCombination(StringCombination stringCombination1, StringCombination stringCombination2) {
        // if lengths of combinations is not equal return null
        if(stringCombination1.getLength() != stringCombination2.getLength())
            return null;


        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < stringCombination1.getLength(); i++) {
            // if the combination cannot be merged return null
            if(stringCombination1.getStringList().get(i)!=null && stringCombination2.getStringList().get(i)!=null)
                return null;

            // merge
            if(stringCombination1.getStringList().get(i)!=null)
                stringList.add(stringCombination1.getStringList().get(i));
            else
                stringList.add(stringCombination2.getStringList().get(i));
        }

        return new StringCombination(stringList);
    }
}
