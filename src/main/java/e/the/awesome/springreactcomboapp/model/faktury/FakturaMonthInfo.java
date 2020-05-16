package e.the.awesome.springreactcomboapp.model.faktury;

public class FakturaMonthInfo {
    private int Month;
    private double Total;

    public FakturaMonthInfo(int month, double total) {
        Month = month;
        Total = total;
    }

    public FakturaMonthInfo(){

    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }


}
