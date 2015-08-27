package rentalcars.converter;

/**
 * Created by zais on 8/27/2015.
 */
public class RomanResult {
    private String roman;
    private String result;

    public RomanResult(String roman, String result) {
        this.result = result;
        this.roman = roman;
    }

    public String getRoman() {
        return roman;
    }

    public void setRoman(String roman) {
        this.roman = roman;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
