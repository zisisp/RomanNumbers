package romantonumber.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.Assert;

@RunWith(JUnit4.class)
public class RomanNumeralGeneratorImplTest {

  private static final String MUST_RETURN = "Must return ";
  private static final String MINUS_ONE = MUST_RETURN + " -1";
  private RomanNumeralGeneratorImpl generator = new RomanNumeralGeneratorImpl();


  @Test
  public void testCornerCases() {
    checkArabicToRomanCustomMessage(0, "Zero numbers are not supported");
    checkArabicToRomanCustomMessage(-1, "Negative numbers are not supported");
    checkArabicToRomanCustomMessage(4000, "Only support numbers up to 3999");
  }

  @Test
  public void testArabicToRoman() {
    checkArabicToRoman(1, "I");
    checkArabicToRoman(5, "V");
    checkArabicToRoman(4, "IV");
    checkArabicToRoman(6, "VI");
    checkArabicToRoman(16, "XVI");
    checkArabicToRoman(19, "XIX");
    checkArabicToRoman(99, "XCIX");
    checkArabicToRoman(256, "CCLVI");
    checkArabicToRoman(513, "DXIII");
    checkArabicToRoman(1954, "MCMLIV");
    checkArabicToRoman(1990, "MCMXC");
    checkArabicToRoman(2014, "MMXIV");
    checkArabicToRoman(3000, "MMM");
  }

  @Test
  public void testRomanToArabic() {
    checkRomanToArabic("MCMLIV", 1954, MUST_RETURN);
    checkRomanToArabic("I", 1, MUST_RETURN);
    checkRomanToArabic("V", 5, MUST_RETURN);
    checkRomanToArabic("IV", 4, MUST_RETURN);
    checkRomanToArabic("VI", 6, MUST_RETURN);
    checkRomanToArabic("XVI", 16, MUST_RETURN);
    checkRomanToArabic("XIX", 19, MUST_RETURN);
    checkRomanToArabic("XCIX", 99, MUST_RETURN);
    checkRomanToArabic("CCLVI", 256, MUST_RETURN);
    checkRomanToArabic("DXIII", 513, MUST_RETURN);
    checkRomanToArabic("MCMXC", 1990, MUST_RETURN);
    checkRomanToArabic("MMXIV", 2014, MUST_RETURN);
    checkRomanToArabic("MCMLIV", 1954, MUST_RETURN);
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
    Assert.isTrue(expectedResult == result,
        failureMessage + expectedResult + " but returned:" + result + " for input:" + roman);
  }


  private void checkArabicToRomanCustomMessage(int number,
      String failureMessage) {
    String result = generator.generate(number);
    Assert.isTrue(result.equals(RomanNumeralGeneratorImpl.NOT_SUPPORTED),
        failureMessage + " but returned:" + result + " for input:" + number);
  }

  private void checkArabicToRoman(int number, String expectedResult) {
    String result = generator.generate(number);
    Assert.isTrue(result.equals(expectedResult),
        RomanNumeralGeneratorImplTest.MUST_RETURN + expectedResult + " but returned:" + result
            + " for input:" + number);
  }
}