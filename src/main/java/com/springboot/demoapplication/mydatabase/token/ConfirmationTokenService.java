package com.springboot.demoapplication.mydatabase.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

      private  final  ConfirmationTokenRepository confirmationTokenRepository;

//    public static Optional<Object> getToken(String token) {
//        return null;
//    }

    public void saveConfirmationToken(ConfirmationToken token){
          confirmationTokenRepository.save(token);
      }

}
