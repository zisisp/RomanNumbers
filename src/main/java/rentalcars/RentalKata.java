package rentalcars;

import rentalcars.converter.RomanNumeralGenerator;
import rentalcars.converter.RomanNumeralGeneratorImpl;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zep
 * Date: 25/8/2015
 * Time: 5:32 μμ
 * Company: www.xe.gr
 */
public class RentalKata {
    private static RomanNumeralGeneratorImpl generator = new RomanNumeralGeneratorImpl();

    public static void main(String[] args) {
        Collection<String> testResults = runTests();
        displayTestResults(testResults);
    }

    private static Collection<String> runTests() {
        Collection<String> testResults = new ArrayList<>();
        //test corner cases
        testResults.add(checkArabicToRoman(0, "Not supported", "Zero numbers are not supported"));
        testResults.add(checkArabicToRoman(-1, "Not supported", "Negative numbers are not supported"));
        testResults.add(checkArabicToRoman(4000, "Not supported", "Only support numbers up to 3999"));

        //test failures in arabicToRoman
        testResults.add(checkArabicToRoman(1, "I", "Should return I"));
        testResults.add(checkArabicToRoman(5, "V", "Should return V"));
        testResults.add(checkArabicToRoman(4, "IV", "Should return IV"));
        testResults.add(checkArabicToRoman(6, "VI", "Should return VI"));
        testResults.add(checkArabicToRoman(16, "XVI", "Should return XVI"));
        testResults.add(checkArabicToRoman(19, "XIX", "Should return XIX"));
        testResults.add(checkArabicToRoman(99, "XCIX", "Should return XCIX"));
        testResults.add(checkArabicToRoman(256, "CCLVI", "Should return CCLVI"));
        testResults.add(checkArabicToRoman(513, "DXIII", "Should return DXIII"));
        testResults.add(checkArabicToRoman(1990, "MCMXC", "Should return MCMXC"));
        testResults.add(checkArabicToRoman(2014, "MMXIV", "Should return MMXIV"));
        testResults.add(checkArabicToRoman(1954, "MCMLIV", "Should return MCMLIV"));

        //test results for RomanToArabic
        testResults.add(checkRomanToArabic("MCMLIV", 1954, "Should return 1954"));
        testResults.add(checkRomanToArabic("I", 1, "Should return 1"));
        testResults.add(checkRomanToArabic("V", 5, "Should return 5"));
        testResults.add(checkRomanToArabic("IV", 4, "Should return 4"));
        testResults.add(checkRomanToArabic("VI", 6, "Should return 6"));
        testResults.add(checkRomanToArabic("XVI", 16, "Should return 16"));
        testResults.add(checkRomanToArabic("XIX", 19, "Should return 19"));
        testResults.add(checkRomanToArabic("XCIX", 99, "Should return 99"));
        testResults.add(checkRomanToArabic("CCLVI", 256, "Should return 256"));
        testResults.add(checkRomanToArabic("DXIII", 513, "Should return 513"));
        testResults.add(checkRomanToArabic("MCMXC", 1990, "Should return 1990"));
        testResults.add(checkRomanToArabic("MMXIV", 2014, "Should return 2014"));
        testResults.add(checkRomanToArabic("MCMLIV", 1954, "Should return 1954"));

        //check failed format
        testResults.add(checkRomanToArabic("IM", -1, "Should return -1 not 1999"));
        testResults.add(checkRomanToArabic("IC", -1, "Should return -1 not 99"));
        testResults.add(checkRomanToArabic("ID", -1, "Should return -1 not 499"));

        return testResults;
    }

    private static String checkRomanToArabic(String roman, int expectedResult, String failureMessage) {
        int result = generator.parse(roman);
        if (result != expectedResult) {
            return failureMessage + " but returned:" + result + " for input:" + roman;
        } else return "OK";
    }


    private static void displayTestResults(Collection<String> testResults) {
        int testsOK = 0;
        Collection<String> failMessages = new ArrayList<>();
        for (String testResult : testResults) {
            if (testResult.equals("OK")) {
                testsOK++;
            } else {
                failMessages.add(testResult);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Test Results:\n");
        if (testsOK == testResults.size()) {
            sb.append("In a total of :" + testResults.size() + " all tests succeeded!");
        } else {
            sb.append("In a total of :" + testResults.size() + " we had " + testsOK + " succesful and " + failMessages.size() + " failed with the following messages:\n");
            for (String failMessage : failMessages) {
                sb.append(failMessage).append("\n");
            }
        }
        System.out.println(sb.toString());
    }


    private static String checkArabicToRoman(int number, String expectedResult, String failureMessage) {
        String result = generator.generate(number);
        if (!result.equals(expectedResult)) {
            return failureMessage + " but returned:" + result + " for input:" + number;
        } else return "OK";
    }
}