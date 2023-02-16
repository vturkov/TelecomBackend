package Telecom.services;
import Telecom.repositories.CallRepository;
import Telecom.DVO.CallErrorVO;
import Telecom.repositories.UserRepository;
import Telecom.entities.Call;
import Telecom.entities.User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CallServiceImpl implements CallService {
    @Autowired
    CallRepository callRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CallErrorVO create(Integer callerId, Integer calleeId, Call call) {
        Optional<User> userOptional = userRepository.findById(callerId);
        if (userOptional.isEmpty()) {
            return new CallErrorVO(1, "No such caller");
        }
        User caller = userOptional.get();
        if (!caller.getConnected()) {
            return new CallErrorVO(2, "Caller not connected");
        }
        if (caller.getBusy()) {
            return new CallErrorVO(3, "Caller is busy");
        }

        userOptional = userRepository.findById(calleeId);
        if (userOptional.isEmpty()) {
            return new CallErrorVO(4, "No such callee");
        }
        User callee = userOptional.get();
        if (!callee.getConnected()) {
            return new CallErrorVO(5, "Callee not connected");
        }
        if (callee.getBusy()) {
            return new CallErrorVO(6, "Callee is busy");
        }

        call.setEnd("");
        call.setCallee(callee);
        call.setCaller(caller);
        call.setPricePerMinute(1);

        Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();

        call.setStart(String.valueOf(timeMilli));

        return new CallErrorVO(0, "");
    }

    @Override
    public CallErrorVO stop(Integer callId, Call call) {
        return null;
    }


//    @Override
//    public Iterable<UniversityVO> getAllProjects() {
//        return Streams.stream(universityRepository.findAll())
//                .map(x -> x.getVO()).toList();
//    }
//
//    @Override
//    public Optional<University> findById(Integer id) {
//        return universityRepository.findById(Integer.valueOf(id));
//    }
//
//    @Override
//    public Iterable<ProgramVO> getPrograms(Integer id) {
//        Optional<University> university = findById(id);
//        if (university.isEmpty()) {
//            return null;
//        }
//
//        return Streams.stream(university.get().getProgram())
//                .map(x -> x.getVO()).toList();
//    }
}

