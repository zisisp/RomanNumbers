package romanNumbers.converter;

/**
 * Created with IntelliJ IDEA.
 * User: zep
 * Date: 28/8/2015
 * Time: 1:50 μμ
 * Company: zisis pontikas
 */
public abstract class Result {
    protected String result;

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
