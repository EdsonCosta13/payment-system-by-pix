package com.edsoncosta.paymentSystem.services;


import com.edsoncosta.paymentSystem.domain.dtos.UserResponseDTO;
import com.edsoncosta.paymentSystem.domain.models.User;
import com.edsoncosta.paymentSystem.domain.respositories.UserRepository;
import com.edsoncosta.paymentSystem.utils.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public UserResponseDTO registerUser(User user) throws MessagingException, UnsupportedEncodingException {

        if(userRepository.findByEmail(user.getEmail()) !=null )
        {
            throw new RuntimeException("This email already exists!");
        }

        String encodedPassword=passwordEncoder.encode(user.getPassword());
        String randomCode= RandomString.generateRandomString(64);

        user.setPassword(encodedPassword);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        User userSaved=this.userRepository.save(user);
        UserResponseDTO userReponse=new UserResponseDTO(userSaved.getId(),userSaved.getName(),userSaved.getEmail(),userSaved.getPassword());

        this.mailService.sendVerificationEmail(user);
        return userReponse;
    }

    public boolean verify(String verificationCode)
    {
        User user=this.userRepository.findByVerificationCode(verificationCode);

        if(user==null || user.isEnabled())
        {
            return false;
        }else{
            user.setVerificationCode(null);
            user.setEnabled(true);

            this.userRepository.save(user);

            return true;
        }
    }


}
