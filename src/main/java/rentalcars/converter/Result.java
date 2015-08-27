package rentalcars.converter;

/**
 * Created by zais on 8/27/2015.
 */
public abstract class Result {
    private String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
