package project1.domain;
 
public class YearlySale {
    //차종 / 건수 / 총금액
    private String carName;
    private int count;
    private int totalPay;
 
    public YearlySale() {
    }
 
    public YearlySale(String carName, int count, int totalPay) {
        super();
        this.carName = carName;
        this.count = count;
        this.totalPay = totalPay;
    }
 
    public String getCarName() {
        return carName;
    }
 
    public void setCarName(String carName) {
        this.carName = carName;
    }
 
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
 
    public int getTotalPay() {
        return totalPay;
    }
 
    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }
 
    @Override
    public String toString() {
        return String.format("%s / %d건 / %,d원", this.getCarName(), this.getCount(), this.getTotalPay());
    }
 
}