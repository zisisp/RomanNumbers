package romanNumbers.converter;

/**
 * Created by zais on 8/26/2015.
 */
public interface RomanNumeralGenerator {
    String generate(int normalNumeral); // convert from int -> roman

    Integer parse(String romanNumber); // convert from roman -> int
}
