package romanNumbers.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.Assert;
@RunWith(JUnit4.class)
public class RomanNumeralGeneratorImplTest {
    private RomanNumeralGeneratorImpl generator = new RomanNumeralGeneratorImpl();

    @Test
    public void testCornerCases() {
        checkArabicToRoman(0, "Not supported", "Zero numbers are not supported");
        checkArabicToRoman(-1, "Not supported", "Negative numbers are not supported");
        checkArabicToRoman(4000, "Not supported", "Only support numbers up to 3999");
    }

    @Test
    public void testArabicToRoman() {
        checkArabicToRoman(1, "I", "Should return I");
        checkArabicToRoman(5, "V", "Should return V");
        checkArabicToRoman(4, "IV", "Should return IV");
        checkArabicToRoman(6, "VI", "Should return VI");
        checkArabicToRoman(16, "XVI", "Should return XVI");
        checkArabicToRoman(19, "XIX", "Should return XIX");
        checkArabicToRoman(99, "XCIX", "Should return XCIX");
        checkArabicToRoman(256, "CCLVI", "Should return CCLVI");
        checkArabicToRoman(513, "DXIII", "Should return DXIII");
        checkArabicToRoman(1954, "MCMLIV", "Should return MCMLIV");
        checkArabicToRoman(1990, "MCMXC", "Should return MCMXC");
        checkArabicToRoman(2014, "MMXIV", "Should return MMXIV");
        checkArabicToRoman(3000, "MMM", "Should return MMM");
    }

    @Test
    public void testRomanToArabic() {
        checkRomanToArabic("MCMLIV", 1954, "Should return 1954");
        checkRomanToArabic("I", 1, "Should return 1");
        checkRomanToArabic("V", 5, "Should return 5");
        checkRomanToArabic("IV", 4, "Should return 4");
        checkRomanToArabic("VI", 6, "Should return 6");
        checkRomanToArabic("XVI", 16, "Should return 16");
        checkRomanToArabic("XIX", 19, "Should return 19");
        checkRomanToArabic("XCIX", 99, "Should return 99");
        checkRomanToArabic("CCLVI", 256, "Should return 256");
        checkRomanToArabic("DXIII", 513, "Should return 513");
        checkRomanToArabic("MCMXC", 1990, "Should return 1990");
        checkRomanToArabic("MMXIV", 2014, "Should return 2014");
        checkRomanToArabic("MCMLIV", 1954, "Should return 1954");
    }

    @Test
    public void testFormatIsAsExpected() {
        //check failed format
        checkRomanToArabic("IM", -1, "Should return -1 not 1999");
        checkRomanToArabic("IC", -1, "Should return -1 not 99");
        checkRomanToArabic("ID", -1, "Should return -1 not 499");
    }

    @Test
    public void checkThatWrongInputReturnsMinusOne() {
        checkRomanToArabic("ABCD", -1, "Should return -1");
        checkRomanToArabic("aasd", -1, "Should return -1");
        checkRomanToArabic(".,1;", -1, "Should return -1");
        checkRomanToArabic("1123", -1, "Should return -1");
        checkRomanToArabic("", -1, "Should return -1");
        checkRomanToArabic(null, -1, "Should return -1");
    }

    private void checkRomanToArabic(String roman, int expectedResult, String failureMessage) {
        int result = generator.parse(roman);
        Assert.isTrue(expectedResult==result, failureMessage + " but returned:" + result + " for input:" + roman);
    }


    private void checkArabicToRoman(int number, String expectedResult, String failureMessage) {
        String result = generator.generate(number);
        Assert.isTrue(result.equals(expectedResult),failureMessage + " but returned:" + result + " for input:" + number);
    }
}