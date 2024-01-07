package com.edsoncosta.paymentSystem.services;


import com.edsoncosta.paymentSystem.domain.dtos.UserResponseDTO;
import com.edsoncosta.paymentSystem.domain.models.User;
import com.edsoncosta.paymentSystem.domain.respositories.UserRepository;
import com.edsoncosta.paymentSystem.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO registerUser(User user) throws Exception {

        if(userRepository.findByEmail(user.getEmail()) !=null )
        {
            throw new Exception("This email already exists!");
        }

        String encodedPassword=passwordEncoder.encode(user.getPassword());
        String randomCode= RandomString.generateRandomString(64);

        user.setPassword(encodedPassword);
        user.setVerificatioCode(randomCode);
        user.setEnabled(false);

        User userSaved=this.userRepository.save(user);
        UserResponseDTO userReponse=new UserResponseDTO(userSaved.getId(),userSaved.getName(),userSaved.getEmail(),userSaved.getPassword());

        return userReponse;
    }


}
