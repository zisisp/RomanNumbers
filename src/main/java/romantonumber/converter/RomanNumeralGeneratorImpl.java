package romantonumber.converter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

/**
 * Roman Numeral Generator
 * The convention used in the convention is that we use a max of 3 consecutive numerals and then we use the next in line
 * and subtract from it the previous power. e.g.  4 is IV and not IIII
 */
public class RomanNumeralGeneratorImpl implements RomanNumeralGenerator {
    private static final String I = "I";//1
    private static final String V = "V";//5
    private static final String X = "X";//10
    private static final String L = "L";//50
    private static final String C = "C";//100
    private static final String D = "D";//500
    private static final String M = "M";//1000
    static final String NOT_SUPPORTED = "Not supported. Only numbers from 1 to 3999 are supported.";
    private static final int FAILED_TO_PARSE = Integer.MIN_VALUE;
    private static final Integer RETURN_ON_FAILURE = -1;

    private static Map<Integer, String> arabicToRoman;
    private static Map<String, Integer> romanToArabic;

    static {
        Map<Integer, String> tempArabicToRoman = new HashMap<>();
        tempArabicToRoman.put(1, I);
        tempArabicToRoman.put(5, V);
        tempArabicToRoman.put(10, X);
        tempArabicToRoman.put(50, L);
        tempArabicToRoman.put(100, C);
        tempArabicToRoman.put(500, D);
        tempArabicToRoman.put(1000, M);
        arabicToRoman = Collections.unmodifiableMap(tempArabicToRoman);
        Map<String, Integer> tempRomanToArabic = new HashMap<>();
        tempRomanToArabic.put(I, 1);
        tempRomanToArabic.put(V, 5);
        tempRomanToArabic.put(X, 10);
        tempRomanToArabic.put(L, 50);
        tempRomanToArabic.put(C, 100);
        tempRomanToArabic.put(D, 500);
        tempRomanToArabic.put(M, 1000);
        romanToArabic = Collections.unmodifiableMap(tempRomanToArabic);
    }

    @Override
    public Integer parse(String romanNumber) {
        if (emptyInput(romanNumber)) return RETURN_ON_FAILURE;
        char[] romanNumbersChar = romanNumber.toCharArray();
        int toReturn = 0;
        for (int i = 0; i < romanNumbersChar.length; i++) {
            Integer arabicToAdd = romanToArabic.getOrDefault(Character.toString(romanNumbersChar[i]), FAILED_TO_PARSE);
            arabicToAdd = checkAndUpdateIfNeededArabicNumberToAdd(romanNumbersChar, i, arabicToAdd);
            if (arabicToAdd == FAILED_TO_PARSE) return RETURN_ON_FAILURE;
            toReturn += arabicToAdd;
        }
        return toReturn;
    }

    private Integer checkAndUpdateIfNeededArabicNumberToAdd(char[] romanNumbersChar, int i, Integer arabicToAdd) {
        boolean hasAnotherRomanNumber = (i + 1) != romanNumbersChar.length;
        if (hasAnotherRomanNumber) {
            arabicToAdd = handleHavingAnotherNumberNext(romanNumbersChar[i + 1], arabicToAdd);
        }
        return arabicToAdd;
    }

    private Integer handleHavingAnotherNumberNext(char c, Integer arabicToAdd) {
        Integer nextNumber = romanToArabic.getOrDefault(Character.toString(c), FAILED_TO_PARSE);
        if (nextNumber > arabicToAdd) {
            if (formatIsInvalid(nextNumber, arabicToAdd)) {
                arabicToAdd = FAILED_TO_PARSE;
            } else {
                arabicToAdd = -arabicToAdd;
            }
        }
        return arabicToAdd;
    }


    private boolean emptyInput(String romanNumber) {
        return romanNumber == null || romanNumber.length() == 0;
    }

    @Override
    public String generate(int normalNumeral) {
        if (!numberIsSupported(normalNumeral)) {//1
            return NOT_SUPPORTED;
        } else {
            return generateRoman(normalNumeral);
        }
    }

    private String generateRoman(int arabic) {
        char[] numberStr = (arabic + "").toCharArray();//1
        StringBuilder stringBuilder = new StringBuilder();    //1
        for (int i = numberStr.length; i > 0; i--) {        //numberStr.length = N
            String strValueOfCharToParse = Character.toString(numberStr[numberStr.length - i]);//1
            int numberToConvert = Integer.parseInt(strValueOfCharToParse);//1
            stringBuilder.append(romansFor((int) Math.pow(10, i - 1), numberToConvert));
        }
        return stringBuilder.toString();
    }


    private String romansFor(int i, int numberToConvert) {
        String baseRoman = arabicToRoman.get(i); //1
        switch (numberToConvert) {
            case 0:
            case 1:
            case 2:
            case 3://up to 3 we add this number
                return addRomanNumbersToBaseNumber(baseRoman, numberToConvert);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                String romanFive = arabicToRoman.get(i * 5);//get next roman number
                if (numberToConvert == 4) {             //1
                    return baseRoman + romanFive;
                } else {
                    return romanFive + addRomanNumbersToBaseNumber(baseRoman, numberToConvert - 5);
                }
            case 9:
            default:
                String romanTen = arabicToRoman.get(i * 10); //1
                return baseRoman + romanTen;                 //1
        }
    }

    private String addRomanNumbersToBaseNumber(String baseRoman, int i) {
        return IntStream
                .range(0, i)
                .boxed()
                .map(x -> baseRoman)
                .collect(joining());
    }

    /**
     * we only support 1 to 3999
     * @param normalNumeral
     * @return
     */
    private boolean numberIsSupported(int normalNumeral) {
        return normalNumeral > 0 && normalNumeral < 4000;
    }

    /**
     * format is invalid if not all consecutive numbers are used for example: 999 cannot be IM and 1999 cannot be MIM.
     *
     * @param greaterThan x
     * @param toAdd       x
     * @return true if format is invalid
     */
    private boolean formatIsInvalid(Integer greaterThan, int toAdd) {
        return greaterThan > toAdd * 10;
    }

}
