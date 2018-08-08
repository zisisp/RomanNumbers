package romanNumbers.converter;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import romanNumbers.Application;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by zais on 8/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RomanNumberGeneratorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testParse() throws Exception {
        //test results for RomanToArabic
        testParseService("V",5, RomanNumberGeneratorController.OK);
        testParseService("MCMLIV", 1954, RomanNumberGeneratorController.OK);
        testParseService("I", 1, RomanNumberGeneratorController.OK);
        testParseService("V", 5, RomanNumberGeneratorController.OK);
        testParseService("IV", 4, RomanNumberGeneratorController.OK);
        testParseService("VI", 6, RomanNumberGeneratorController.OK);
        testParseService("XVI", 16, RomanNumberGeneratorController.OK);
        testParseService("XIX", 19, RomanNumberGeneratorController.OK);
        testParseService("XCIX", 99, RomanNumberGeneratorController.OK);
        testParseService("CCLVI", 256, RomanNumberGeneratorController.OK);
        testParseService("DXIII", 513, RomanNumberGeneratorController.OK);
        testParseService("MCMXC", 1990, RomanNumberGeneratorController.OK);
        testParseService("MMXIV", 2014, RomanNumberGeneratorController.OK);
        testParseService("MCMLIV", 1954, RomanNumberGeneratorController.OK);

        //check failed format
        testParseService("IM", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("IC", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("ID", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        //check wrong input

        testParseService("ABCD", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("aasd", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService(".,1;", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("1123", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("1123", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService("", -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testParseService(null, -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
    }


    @Test
    public void testGenerate() throws Exception {
        //test corner cases
        testGenerateService("0",null,RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testGenerateService("-1",null,RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testGenerateService("4000",null,RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        testGenerateService("qwer",null,RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        //test failures in arabicToRoman
        testGenerateService("1", "I", RomanNumberGeneratorController.OK);
        testGenerateService("5", "V", RomanNumberGeneratorController.OK);
        testGenerateService("4", "IV", RomanNumberGeneratorController.OK);
        testGenerateService("6", "VI", RomanNumberGeneratorController.OK);
        testGenerateService("16", "XVI", RomanNumberGeneratorController.OK);
        testGenerateService("19", "XIX", RomanNumberGeneratorController.OK);
        testGenerateService("99", "XCIX", RomanNumberGeneratorController.OK);
        testGenerateService("256", "CCLVI", RomanNumberGeneratorController.OK);
        testGenerateService("513", "DXIII", RomanNumberGeneratorController.OK);
        testGenerateService("1990", "MCMXC", RomanNumberGeneratorController.OK);
        testGenerateService("2014", "MMXIV", RomanNumberGeneratorController.OK);
        testGenerateService("1954", "MCMLIV", RomanNumberGeneratorController.OK);

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
        mockMvc.perform(get("/generate?number="+number))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.roman", emptyOrNullString))
                .andExpect(jsonPath("$.result",is(result)));
        System.out.println("Test for number:"+number+" to roman:"+roman+" is OK");
    }
    
    
}