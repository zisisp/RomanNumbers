package romannumbers.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.Assert;
@RunWith(JUnit4.class)
public class RomanNumeralGeneratorImplTest {

    private static final String NOT_SUPPORTED = "Not supported";
    private static final String MUST_RETURN = "Must return";
    private static final String MINUS_ONE = MUST_RETURN + " -1";
    private RomanNumeralGeneratorImpl generator = new RomanNumeralGeneratorImpl();

    @Test
    public void testCornerCases() {
        checkArabicToRoman(0, NOT_SUPPORTED, "Zero numbers are not supported");
        checkArabicToRoman(-1, NOT_SUPPORTED, "Negative numbers are not supported");
        checkArabicToRoman(4000, NOT_SUPPORTED, "Only support numbers up to 3999");
    }

    @Test
    public void testArabicToRoman() {
        checkArabicToRoman(1, "I", MUST_RETURN + " I");
        checkArabicToRoman(5, "V", MUST_RETURN + " V");
        checkArabicToRoman(4, "IV", MUST_RETURN + " IV");
        checkArabicToRoman(6, "VI", MUST_RETURN + " VI");
        checkArabicToRoman(16, "XVI", MUST_RETURN + " XVI");
        checkArabicToRoman(19, "XIX", MUST_RETURN + " XIX");
        checkArabicToRoman(99, "XCIX", MUST_RETURN + " XCIX");
        checkArabicToRoman(256, "CCLVI", MUST_RETURN + " CCLVI");
        checkArabicToRoman(513, "DXIII", MUST_RETURN + " DXIII");
        checkArabicToRoman(1954, "MCMLIV", MUST_RETURN + " MCMLIV");
        checkArabicToRoman(1990, "MCMXC", MUST_RETURN + " MCMXC");
        checkArabicToRoman(2014, "MMXIV", MUST_RETURN + " MMXIV");
        checkArabicToRoman(3000, "MMM", MUST_RETURN + " MMM");
    }

    @Test
    public void testRomanToArabic() {
        checkRomanToArabic("MCMLIV", 1954, MUST_RETURN + " 1954");
        checkRomanToArabic("I", 1, MUST_RETURN + " 1");
        checkRomanToArabic("V", 5, MUST_RETURN + " 5");
        checkRomanToArabic("IV", 4, MUST_RETURN + " 4");
        checkRomanToArabic("VI", 6, MUST_RETURN + " 6");
        checkRomanToArabic("XVI", 16, MUST_RETURN + " 16");
        checkRomanToArabic("XIX", 19, MUST_RETURN + " 19");
        checkRomanToArabic("XCIX", 99, MUST_RETURN + " 99");
        checkRomanToArabic("CCLVI", 256, MUST_RETURN + " 256");
        checkRomanToArabic("DXIII", 513, MUST_RETURN + " 513");
        checkRomanToArabic("MCMXC", 1990, MUST_RETURN + " 1990");
        checkRomanToArabic("MMXIV", 2014, MUST_RETURN + " 2014");
        checkRomanToArabic("MCMLIV", 1954, MUST_RETURN + " 1954");
    }

    @Test
    public void testFormatIsAsExpected() {
        //check failed format
        checkRomanToArabic("IM", -1, MUST_RETURN + " -1 not 1999");
        checkRomanToArabic("IC", -1, MUST_RETURN + " -1 not 99");
        checkRomanToArabic("ID", -1, MUST_RETURN + " -1 not 499");
    }

    @Test
    public void checkThatWrongInputReturnsMinusOne() {
        checkRomanToArabic("ABCD", -1, MINUS_ONE);
        checkRomanToArabic("aasd", -1, MINUS_ONE);
        checkRomanToArabic(".,1;", -1, MINUS_ONE);
        checkRomanToArabic("1123", -1, MINUS_ONE);
        checkRomanToArabic("", -1, MINUS_ONE);
        checkRomanToArabic(null, -1, MINUS_ONE);
    }

    private void checkRomanToArabic(String roman, int expectedResult, String failureMessage) {
        int result = generator.parse(roman);
        Assert.isTrue(expectedResult==result,
            failureMessage + " but returned:" + result + " for input:" + roman);
    }


    private void checkArabicToRoman(int number, String expectedResult, String failureMessage) {
        String result = generator.generate(number);
        Assert.isTrue(result.equals(expectedResult),
            failureMessage + " but returned:" + result + " for input:" + number);
    }
}