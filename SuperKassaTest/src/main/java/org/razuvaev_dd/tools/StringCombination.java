package org.razuvaev_dd.tools;

import java.util.List;

public class StringCombination {

    List<String> stringList;

    public StringCombination(List<String> stringList) {
        this.stringList = stringList;
    }

    public boolean isFull() {
        return !stringList.contains(null);
    }

    public List<String> getStringList() {
        return stringList;
    }

    public int getLength() {
        return stringList.size();
    }

    @Override
    public String toString() {
        return stringList.toString();
    }
}
