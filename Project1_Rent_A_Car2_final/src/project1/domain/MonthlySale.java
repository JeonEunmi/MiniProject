package project1.domain;
 
public class MonthlySale {
    //�� / �Ǽ� / �ѱݾ�
    private String month;
    private int count;
    private int totalPay;
 
    public MonthlySale() {
    }
 
    public MonthlySale(String month, int count, int totalPay) {
        super();
        this.month = month;
        this.count = count;
        this.totalPay = totalPay;
    }
 
    public String getMonth() {
        return month;
    }
 
    public void setMonth(String month) {
        this.month = month;
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
        return String.format("%s / %d�� / %,d��", this.getMonth(), this.getCount(), this.getTotalPay());
    }
 
}