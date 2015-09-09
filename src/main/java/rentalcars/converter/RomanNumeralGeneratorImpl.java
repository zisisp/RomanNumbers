package rentalcars.converter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zais on 8/26/2015.
 */

/**
 * Roman Numeral Generator
 * The convention used in the convention is that we use a max of 3 consecutive numerals and then we use the next in line
 * and subtract from it the previous power. e.g.  4 is IV and not IIII
 */
public class RomanNumeralGeneratorImpl implements RomanNumeralGenerator {
    public static final String I = "I";//1
    public static final String V = "V";//5
    public static final String X = "X";//10
    public static final String L = "L";//50
    public static final String C = "C";//100
    public static final String D = "D";//500
    public static final String M = "M";//1000
    public static final String NOT_SUPPORTED = "Not supported";

    public static Map<Integer, String> arabicToRoman = new HashMap<>();
    public static Map<String, Integer> romanToArabic = new HashMap<>();

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
        if (emptyInput(romanNumber)) return -1;             //1
        char[] romanNumbersChar= romanNumber.toCharArray(); //1?
        int toReturn = 0;                                   //1?
        for (int i = 0; i < romanNumbersChar.length; i++) { //romanNumbersChar.length ==n (max)
            Integer integer = romanToArabic.get(Character.toString(romanNumbersChar[i]));
            if (checkFailedInput(integer)) return -1;
            int toAdd = integer;
            if ((i + 1) != romanNumbersChar.length && romanToArabic.get(Character.toString(romanNumbersChar[i + 1])) > toAdd) {
                if (formatIsInvalid(romanToArabic.get(Character.toString(romanNumbersChar[i + 1])),toAdd)) {
                    return -1;
                } else
                    toAdd = -toAdd;
            }
            toReturn+=toAdd;
        }
        return toReturn;
    }

    private boolean emptyInput(String romanNumber) {
        return romanNumber == null || romanNumber.length() == 0;
    }

    private boolean checkFailedInput(Integer integer) {
        return integer == null;
    }

    @Override
    public String generate(int normalNumeral) {
        if (!numberIsSupported(normalNumeral)) {//1
            return NOT_SUPPORTED;
        } else {
            return generateRoman(normalNumeral);
        }
    }

    private String generateRoman(int normalNumeral) {
        char[] numberStr = (normalNumeral + "").toCharArray();//1
        StringBuilder stringBuilder = new StringBuilder();    //1
        for (int i = numberStr.length; i > 0; i--) {        //numberStr.length = N
            String strValueofCharToParse = Character.toString(numberStr[numberStr.length - i]);//1
            int numberToConver = Integer.parseInt(strValueofCharToParse);//1
            stringBuilder.append(romansFor((int) Math.pow(10, i - 1), numberToConver));
        }
        return stringBuilder.toString();
    }


    private String romansFor(int i, int numberToConvert) {
        String toReturn = ""; //1
        String baseRoman = arabicToRoman.get(i); //1
        switch (numberToConvert) {
            case 0:
            case 1:
            case 2:
            case 3:
                for (int j = 0; j < numberToConvert; j++) { //0-3
                    toReturn += baseRoman;
                }
                return toReturn;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                String romanFive = arabicToRoman.get(i * 5);//1
                if (numberToConvert == 4) {             //1
                    return baseRoman + romanFive;
                } else {
                    toReturn = romanFive;               //1
                    for (int j = 0; j < numberToConvert - 5; j++) {//0-3
                        toReturn += baseRoman;
                    }
                    return toReturn;                                //1
                }
            case 9:
            default:
                String romanTen = arabicToRoman.get(i * 10); //1
                return baseRoman + romanTen;                 //1
        }
    }

    private boolean numberIsSupported(int normalNumeral) {
        return normalNumeral > 0 && normalNumeral < 4000;
    }

    /**
     * format is invalid if not all consecutive numbers are used for example: 999 cannot be IM and 1999 cannot be MIM.
     * @param greaterThan
     * @param toAdd
     * @return true if format is invalid
     */
    private boolean formatIsInvalid(Integer greaterThan, int toAdd) {
        return greaterThan>toAdd*10;
    }

}
