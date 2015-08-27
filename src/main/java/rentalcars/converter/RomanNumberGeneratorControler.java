package rentalcars.converter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zais on 8/27/2015.
 */
@RestController
public class RomanNumberGeneratorControler {

    public static final String RESULT = "result=";
    private RomanNumeralGenerator romanNumeralGenerator=new RomanNumeralGeneratorImpl();

    @RequestMapping("/parse")
    public Result parse(@RequestParam("roman") String roman) {
        int parsedNumber= romanNumeralGenerator.parse(roman);
        if (parsedNumber==-1) {
            return new NumberResult( -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        } else {
            return new NumberResult(parsedNumber, "OK");
        }
    }

    @RequestMapping("/generate")
    public Result generate(@RequestParam("number") Integer number) {
        String generateRoman = romanNumeralGenerator.generate(number);
        if (RomanNumeralGeneratorImpl.NOT_SUPPORTED.equals(generateRoman)) {
            return new RomanResult(null, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        } else {
            return new RomanResult(generateRoman, "OK");
        }
    }

}
