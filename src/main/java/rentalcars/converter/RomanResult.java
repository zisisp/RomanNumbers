package rentalcars.converter;

/**
 * Created by zais on 8/27/2015.
 */
public class RomanResult extends Result {
    private String roman;
    public RomanResult(String roman, String result) {
        super(result);
        this.roman = roman;
    }

    public String getRoman() {
        return roman;
    }

    public void setRoman(String roman) {
        this.roman = roman;
    }
}
