package Telecom.DVO;

import Telecom.entities.Call;

public class CallVO {
    public Integer id;
    public Integer caller_id;

    public String caller_phone;

    public Integer callee_id;

    public String callee_phone;

    public Integer price_per_minute;
    public String start;
    public String end;


    public CallVO(Call call) {
        id = call.getId();
        caller_id = call.getCaller().getId();
        caller_phone = call.getCaller().getPhone();
        callee_id = call.getCaller().getId();
        callee_phone = call.getCaller().getPhone();
        price_per_minute = call.getPricePerMinute();
        start = call.getStart();
        end = call.getEnd();
    }
}
