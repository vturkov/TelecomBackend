package Telecom.DVO;

import Telecom.entities.User;

public class UserVO {
    public Integer id;
    public String FIO;

    public String phone;
    public Integer balance;

    public Boolean connected;

    public Boolean busy;

    public UserVO(User user) {
        id = user.getId();
        FIO = user.getFIO();
        phone = user.getPhone();
        balance = user.getBalance();
        connected = user.getConnected();
        busy = user.getBusy();
    }
}
