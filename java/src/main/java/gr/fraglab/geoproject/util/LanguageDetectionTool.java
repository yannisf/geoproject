package gr.fraglab.geoproject.util;

public class LanguageDetectionTool {

    private LanguageDetectionTool() {
    }

    public static Character.UnicodeScript detect(String input) {
        int[] counts = new int[Character.UnicodeScript.values().length];

        Character.UnicodeScript mostFrequentScript = null;
        int maxCount = 0;

        int n = input.codePointCount(0, input.length());
        for (int i = 0; i < n; i = input.offsetByCodePoints(i, 1)) {
            int codePoint = input.codePointAt(i);
            Character.UnicodeScript script = Character.UnicodeScript.of(codePoint);

            int count = ++counts[script.ordinal()];
            if (mostFrequentScript == null || count > maxCount) {
                maxCount = count;
                mostFrequentScript = script;
            }
        }

        return mostFrequentScript;
    }
}
