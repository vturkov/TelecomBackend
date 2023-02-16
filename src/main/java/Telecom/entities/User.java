package Telecom.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;
import Telecom.DVO.UserVO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;

    private String FIO;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isBusy() {
        return busy;
    }

    private String phone;

    public Integer balance;

    public boolean connected;

    public boolean busy;

    @Lazy
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="calls")
    @JoinColumn(name = "callee_id", referencedColumnName = "id")
    private List<Call> incomingCalls = new ArrayList<Call>();
    @Lazy
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="calls")
    @JoinColumn(name = "caller_id", referencedColumnName = "id")
    private List<Call> outComingCalls = new ArrayList<Call>();;

    public User(String fio, String phone) {
        this.FIO = fio;
        this.phone = phone;
        this.balance = 0;
        this.connected = false;
        this.busy = false;
    }

    public User() {
        this.FIO = "IVAN";
        this.phone = "89212223344";
        this.balance = 0;
        this.connected = false;
        this.busy = false;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String name) {
        this.FIO = name;
    }

    public List<Call> getIncomingCalls() {
        return incomingCalls;
    }

    public List<Call> getOutComingCalls() {
        return outComingCalls;
    }

    public void setIncomingCalls(List<Call>  university) {
        this.incomingCalls = incomingCalls;
    }

    public void setOutComingCalls(List<Call>  university) {
        this.outComingCalls = outComingCalls;
    }

    public Integer getBalance() {
        return balance;
    }

    public Boolean getConnected() {
        return connected;
    }

    public Boolean getBusy() {
        return busy;
    }

    public UserVO getVO() {
        return new UserVO(this);
    }

    public String getPhone() {
        return phone;
    }

    public void setBalance(int i) {
        balance = i;
    }

    public void setConnected(boolean b) {
        connected = b;
    }
}
