package Telecom.DVO;

public class CallErrorVO {
    public Integer code;
    String message;

    public CallErrorVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
