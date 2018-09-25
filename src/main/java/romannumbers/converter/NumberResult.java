package romannumbers.converter;

/**
 * Created by zpontikas on 8/27/2015.
 */
public class NumberResult extends Result {
    private Integer number;

    public NumberResult(int number, String result) {
        super(result);
        this.number=number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
