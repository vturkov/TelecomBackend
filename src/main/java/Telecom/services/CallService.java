package Telecom.services;


import Telecom.DVO.CallErrorVO;
import Telecom.entities.Call;

import java.util.List;
import java.util.Optional;

public interface CallService {
    CallErrorVO create(Integer callerId, Integer calleeId, Call call);

    CallErrorVO stop(Integer callId, Call call);
//    public Iterable<UniversityVO> getAllProjects();
//    public Optional<University> findById(Integer id);
//
//    Iterable<ProgramVO> getPrograms(Integer id);
}
