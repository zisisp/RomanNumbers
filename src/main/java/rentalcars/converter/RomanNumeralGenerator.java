package rentalcars.converter;

/**
 * Created by zais on 8/26/2015.
 */
public interface RomanNumeralGenerator {
    public String generate(int normalNumeral); // convert from int -> roman

    public Integer parse(String romanNumber); // convert from roman -> int
}
