package Telecom.services;


import Telecom.DVO.BalanceVO;
import Telecom.DVO.CallVO;
import Telecom.DVO.UserVO;
import Telecom.entities.User;

import java.util.List;

public interface UserService {
    UserVO createUser(String fio, String phone);

    BalanceVO deposit(Integer userId, Integer amount);

    BalanceVO getBalance(Integer userId);

    UserVO connect(Integer userId);

    UserVO disconnect(Integer userId);

    UserVO findByPhone(String phone);

    List<CallVO> getIncomingCalls(Integer userId);

    List<CallVO> getOutComingCalls(Integer userId);

    List<UserVO> getAllUsers();
//    public Iterable<TagVO> getAllTags();
//
//    Iterable<ProgramVO> findProgramsWithSuchTags(List<Integer> tags);
}
