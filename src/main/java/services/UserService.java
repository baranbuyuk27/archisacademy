package services;


import entities.User;
import exceptions.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        userWithSameUsernameShouldNotExist(user.getUsername());
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        Optional<User> user= userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new EntityNotFoundException("User not found");
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        User existingUser =getUserById(id);
        userRepository.delete(existingUser);
    }


    public User updateUser(Long id,User userDetails){
        userWithSameUsernameShouldNotExist(userDetails.getUsername());

        User existingUser =getUserById(id);
        existingUser.setName(userDetails.getName());
        existingUser.setSurname(userDetails.getSurname());

        return userRepository.save(existingUser);
    }


    private void userWithSameUsernameShouldNotExist(String username){
        User userWithSameUsername = userRepository.findByUsername(username);
        if (userWithSameUsername != null){
            //exception fırlatacağımız yer diyelim
            throw new BusinessException("Bu username sahip bir kullanıcı mevcut...");
        }
    }



}
