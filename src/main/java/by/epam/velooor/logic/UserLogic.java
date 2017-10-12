package by.epam.velooor.logic;

import by.epam.velooor.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import by.epam.velooor.entity.Apartment;
import by.epam.velooor.entity.User;
import by.epam.velooor.exception.LogicException;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserLogic {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) throws LogicException {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new LogicException("No user with id = " + id);
        }
        return user;
    }

    public User findByLogin(String login) throws LogicException {
        User user = userRepository.findByLoginIgnoreCase(login);
        if (user == null) {
            throw new LogicException("No user with login = " + login);
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllNotBanned() {
        return userRepository.findByBanFalse();
    }

    public List<User> findAllBanned() {
        return userRepository.findByBanTrue();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void delete(int id) throws LogicException {
        try {
            userRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LogicException("There is no user with id = " + id);
        }
    }

    public List<Apartment> getUserApartments(int userId) throws LogicException {
        List<Apartment> bookedApartments = null;
        try {
            User user = findById(userId);
            bookedApartments = new ArrayList<>(user.getApartments());
        } catch (LogicException e) {
            throw new LogicException("Can not get user apartments", e);
        }
        return bookedApartments;
    }
}
