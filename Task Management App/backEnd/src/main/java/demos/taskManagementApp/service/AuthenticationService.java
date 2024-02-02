package demos.taskManagementApp.service;

import demos.taskManagementApp.model.User;
import dto.SignUpRequest;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
}
