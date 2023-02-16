package Telecom.services;

import Telecom.DVO.BalanceVO;
import Telecom.DVO.CallVO;
import Telecom.DVO.UserVO;
import Telecom.repositories.UserRepository;
import Telecom.entities.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import jakarta.persistence.PersistenceUnit;
import org.assertj.core.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    // Auto wire EntityManagerFactory
    @PersistenceUnit(unitName = "default")
    private EntityManagerFactory entityManagerFactory;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public UserVO createUser(String fio, String phone) {
        if (!userRepository.findByPhone(phone).isEmpty()) {
            throw new EntityExistsException();
        }

        User user = new User(fio, phone);
        userRepository.save(user);
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(user);
//        em.flush();

        return userRepository.findByPhone(phone).get().getVO();

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public BalanceVO deposit(Integer userId, Integer amount) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();
        Integer newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);

        userRepository.save(user);

        return new BalanceVO(newBalance);
    }

    @Override
    public BalanceVO getBalance(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return new BalanceVO(user.get().getBalance());
    }

    @Override
    public UserVO connect(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();

        user.setConnected(true);
        userRepository.save(user);

        return new UserVO(user);
    }

    @Override
    public UserVO disconnect(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();

        user.setConnected(false);
        userRepository.save(user);

        return new UserVO(user);
    }

    @Override
    public UserVO findByPhone(String phone) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();

        return new UserVO(user);
    }

    @Override
    public List<CallVO> getIncomingCalls(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();

        return Streams.stream(user.getIncomingCalls())
                .map(x -> x.getVO()).toList();
    }

    @Override
    public List<CallVO> getOutComingCalls(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();

        return Streams.stream(user.getIncomingCalls())
                .map(x -> x.getVO()).toList();
    }

    public List<UserVO> getAllUsers() {
        var users = userRepository.findAll();
        return Streams.stream(users)
                .map(x -> x.getVO()).toList();
    }
}