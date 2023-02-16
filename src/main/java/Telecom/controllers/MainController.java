package Telecom.controllers;



import Telecom.DVO.CallErrorVO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.server.ResponseStatusException;
import Telecom.DVO.UserVO;
import Telecom.DVO.BalanceVO;
import Telecom.DVO.CallVO;
import Telecom.entities.User;
import Telecom.entities.Call;
import Telecom.services.UserService;
import Telecom.services.CallService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v0")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    CallService callService;


    @CrossOrigin
    @PostMapping(path = "/user/create")
    public @ResponseBody ResponseEntity<?> createUser(@RequestParam String FIO, @RequestParam String phone) {
        try {
            UserVO user = userService.createUser(FIO, phone);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/user/get")
    public @ResponseBody ResponseEntity<?> findUser(@RequestParam String phone) {
        try {
            UserVO user = userService.findByPhone(phone);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/balance")
    public @ResponseBody ResponseEntity<?> deposit(@RequestParam Integer userId, @RequestParam Integer amount) {
        try {
            BalanceVO balance = userService.deposit(userId, amount);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/balance")
    public @ResponseBody ResponseEntity<?> getBalance(@RequestParam Integer userId) {
        // This returns a JSON or XML with the universities
        try {
            BalanceVO balance = userService.getBalance(userId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<?> getAllUsers() {
        // This returns a JSON or XML with the universities
        try {
            var users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/user/getIncomingCalls")
    public @ResponseBody ResponseEntity<?> getIncomingCalls(@RequestParam Integer userId) {
        // This returns a JSON or XML with the universities
        try {
            List<CallVO> balance = userService.getIncomingCalls(userId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/user/getOutComingCalls")
    public @ResponseBody ResponseEntity<?> getOutComingCalls(@RequestParam Integer userId) {
        // This returns a JSON or XML with the universities
        try {
            List<CallVO> balance = userService.getOutComingCalls(userId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/user/connect")
    public @ResponseBody ResponseEntity<?> connect(@RequestParam Integer userId) {
        try {
            UserVO balance = userService.connect(userId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/user/disconnect")
    public @ResponseBody ResponseEntity<?> disconnect(@RequestParam Integer userId) {
        try {
            UserVO balance = userService.disconnect(userId);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/call/create")
    public @ResponseBody ResponseEntity<?> createCall(@RequestParam Integer callerId, @RequestParam Integer calleeId) {
        Call call = new Call();
        CallErrorVO error = callService.create(callerId, calleeId, call);
        if (error.code == 0) {
            return new ResponseEntity<>(call, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/call/stop")
    public @ResponseBody ResponseEntity<?> stopCall(@RequestParam Integer callId) {
        Call call = new Call();
        CallErrorVO error = callService.stop(callId, call);
        if (error.code == 0) {
            return new ResponseEntity<>(call, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

//    @CrossOrigin
//    @GetMapping(path = "/university")
//    public @ResponseBody Iterable<ProgramVO> getAllPrograms(@RequestParam String id) {
//        // This returns a JSON or XML with programs of the university
//        Iterable<ProgramVO> programs = universityService.getPrograms(Integer.valueOf(id));
//
//        if (programs == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "wrong university id"
//            );
//        }
//
//        return programs;
//    }
//
//    @CrossOrigin
//    @GetMapping(path = "/programs")
//    public @ResponseBody Iterable<ProgramVO> getProgramsByTags(@RequestParam List<Integer> tags) {
//        // This returns a JSON or XML with the programs by tags
//        Iterable<ProgramVO> result = tagService.findProgramsWithSuchTags(tags);
//        // #TO DO fix to exceptions
//        if (result == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "wrong tag"
//            );
//        }
//
//        return result;
//    }
//
//    @CrossOrigin
//    @GetMapping(path = "/tags")
//    public @ResponseBody Iterable<TagVO> getTags() {
//        // This returns a JSON or XML with the all tags
//        return tagService.getAllTags();
//    }
}

