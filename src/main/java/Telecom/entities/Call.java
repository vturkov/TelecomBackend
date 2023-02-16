package Telecom.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import Telecom.DVO.CallVO;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer pricePerMinute;

    public void setPricePerMinute(Integer pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    public void setCallee(User callee) {
        this.callee = callee;
    }

    private String start;

    private String end;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="caller_id", referencedColumnName="id")
    private User caller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="callee_id", referencedColumnName="id")
    private User callee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CallVO getVO() {
        return new CallVO(this);
    }

    public Integer getPricePerMinute() {
        return pricePerMinute;
    }
    public User getCaller() {
        return caller;
    }

    public User getCallee() {
        return callee;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}