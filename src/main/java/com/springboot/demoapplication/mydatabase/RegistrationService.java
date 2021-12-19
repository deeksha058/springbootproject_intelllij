package com.springboot.demoapplication.mydatabase;

import com.springboot.demoapplication.appuser.AppUser;
import com.springboot.demoapplication.appuser.AppUserRole;
import com.springboot.demoapplication.appuser.AppUserService;
import com.springboot.demoapplication.mydatabase.token.ConfirmationToken;
import com.springboot.demoapplication.mydatabase.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
       if (!isValidEmail) {
           throw new IllegalStateException("email not valid");
       }

        return appUserService.signUpUser(
                new AppUser(
                     request.getFirstName(),
                     request.getLastName(),
                     request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
   @Transactional
    public String confirmToken(String token) {
       ConfirmationToken ConfirmationToken = confirmationTokenService

               .getToken(token)
               .orElseThrow(() ->
                       new IllegalStateException("token not found"));

       if (ConfirmationToken.getConfirmedAt() != null){
           throw new IllegalStateException("email already confirmed");
       }

       LocalDateTime expiredAt = ConfirmationToken.getExpiresAt();

       if(expiredAt.isBefore(LocalDateTime.now())){
           throw new IllegalStateException("token expired");
       }

       confirmationTokenService.setConfirmed(token);
       appUserService.enableAppUser(
               confirmationToken.getAppUser().getEmail()
       );
        return "confirmed";
    }
}
