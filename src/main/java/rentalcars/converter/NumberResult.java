package rentalcars.converter;

/**
 * Created by zais on 8/27/2015.
 */
public class NumberResult {
    private Integer number;
    private String result;

    public NumberResult(int number, String result) {
        this.result = result;
        this.number=number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
