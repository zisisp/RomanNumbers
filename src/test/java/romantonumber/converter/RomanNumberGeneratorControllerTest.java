package romantonumber.converter;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static romantonumber.converter.RomanNumberGeneratorController.OK;
import static romantonumber.converter.RomanNumeralGeneratorImpl.NOT_SUPPORTED;

import java.nio.charset.Charset;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by zpontikas on 8/27/2015.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class RomanNumberGeneratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void testParse() throws Exception {
        //test results for RomanToArabic
        testParseService("V",5, OK);
        testParseService("MCMLIV", 1954, OK);
        testParseService("I", 1, OK);
        testParseService("V", 5, OK);
        testParseService("IV", 4, OK);
        testParseService("VI", 6, OK);
        testParseService("XVI", 16, OK);
        testParseService("XIX", 19, OK);
        testParseService("XCIX", 99, OK);
        testParseService("CCLVI", 256, OK);
        testParseService("DXIII", 513, OK);
        testParseService("MCMXC", 1990, OK);
        testParseService("MMXIV", 2014, OK);
        testParseService("MCMLIV", 1954, OK);

        //check failed format
        testParseService("IM", -1, NOT_SUPPORTED);
        testParseService("IC", -1, NOT_SUPPORTED);
        testParseService("ID", -1, NOT_SUPPORTED);
        //check wrong input

        testParseService("ABCD", -1, NOT_SUPPORTED);
        testParseService("aasd", -1, NOT_SUPPORTED);
        testParseService(".,1;", -1, NOT_SUPPORTED);
        testParseService("1123", -1, NOT_SUPPORTED);
        testParseService("1123", -1, NOT_SUPPORTED);
        testParseService("", -1, NOT_SUPPORTED);
        testParseService(null, -1, NOT_SUPPORTED);
    }


    @Test
    public void testGenerate() throws Exception {
        //test corner cases
        testGenerateService("0",null, NOT_SUPPORTED);
        testGenerateService("-1",null, NOT_SUPPORTED);
        testGenerateService("4000",null, NOT_SUPPORTED);
        testGenerateService("qwer",null, NOT_SUPPORTED);
        //test failures in arabicToRoman
        testGenerateService("1", "I", OK);
        testGenerateService("5", "V", OK);
        testGenerateService("4", "IV", OK);
        testGenerateService("6", "VI", OK);
        testGenerateService("16", "XVI", OK);
        testGenerateService("19", "XIX", OK);
        testGenerateService("99", "XCIX", OK);
        testGenerateService("256", "CCLVI", OK);
        testGenerateService("513", "DXIII", OK);
        testGenerateService("1990", "MCMXC", OK);
        testGenerateService("2014", "MMXIV", OK);
        testGenerateService("1954", "MCMLIV", OK);

    }

    private void testParseService(String roman, int number, String result) throws Exception {
        mockMvc.perform(get("/parse?roman="+roman))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.number", is(number)))
                .andExpect(jsonPath("$.result", is(result)));
        System.out.println("Test for roman:"+roman+" to number:"+number+" is OK");
    }

    private void testGenerateService(String number, String roman, String result) throws Exception {
        Matcher<String> emptyOrNullString = roman==null||roman.length()==0?isEmptyOrNullString():is(roman);
        mockMvc.perform(get("/generate?number=" +number))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.roman", emptyOrNullString))
                .andExpect(jsonPath("$.result",is(result)));
        System.out.println("Test for number:"+number+" to roman:"+roman+" is OK");
    }
    
    
}