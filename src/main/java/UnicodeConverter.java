public class UnicodeConverter {

    public String rangeCharacter;
    private final Integer base;

    public UnicodeConverter(String rangeCharacter) {
        this.rangeCharacter = rangeCharacter;
        base = rangeCharacter.length();
    }

    public String unicodeToBase(String unicode) {
        unicode = new StringBuilder(unicode).reverse().toString();
        long valuesChars = 0L;
        for (int i = 0; i < unicode.length(); i++) {
            valuesChars += ((long) unicode.charAt(i) * (long) Math.pow((int) Character.MAX_VALUE, i));
        }
        return Long.toString(valuesChars, base);
    }

    public String unicodeToRangeString(String unicode) {
        return longToUnicode(unicodeToBase(unicode));
    }

    public String longToUnicode(String encoded) {
        long unicodeValue = Long.parseLong(encoded, base);
        StringBuilder result = new StringBuilder();

        while (unicodeValue > 0) {
            int index = (int) (unicodeValue % base);
            result.insert(0, rangeCharacter.charAt(index));
            unicodeValue /= base;
        }

        return result.toString();
    }
}

