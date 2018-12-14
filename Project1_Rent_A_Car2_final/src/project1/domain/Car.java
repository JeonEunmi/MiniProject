package project1.domain;
 
import java.io.Serializable;
 
public class Car implements Serializable {
    
    private static final long serialVersionUID = 4109260190659700349L;
 
    //������ȣ, ����, ũ��(����,����,������,����),ž���ο�, 1�Ϸ�Ʈ��
    private String carId, carName, carSize;
    private int capacity, rentFee;
    
    public Car() {
        
    }
 
    public Car(String carId, String carName, String carSize, int capacity, int rentFee) {
        this.carId = carId;
        this.carName = carName;
        this.carSize = carSize;
        this.capacity = capacity;
        this.rentFee = rentFee;
    }
 
    //������ȣ, ����, ũ��, ž���ο�, 1�Ϸ�Ʈ��� �б� ����
    public String getCarId() {
        return carId;
    }
 
    public String getCarName() {
        return carName;
    }
 
    public String getCarSize() {
        return carSize;
    }
 
    public int getCapacity() {
        return capacity;
    }
 
    public int getRentFee() {
        return rentFee;
    }
 
 
    @Override
    public String toString() {
        return String.format("%s / %s / %s / %s�� / %,d��%n ", this.getCarId(), this.getCarName(),
                this.getCarSize(), this.getCapacity(), this.getRentFee());
    }
    
}
 
