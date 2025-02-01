package src.java.model.Comparator;

import src.java.model.Text;

import java.util.Comparator;

public class TextComparator implements Comparator<Text> {
    @Override
    public int compare(Text a, Text b) {
        return Double.compare(a.getAverage(), b.getAverage());
    }
}
