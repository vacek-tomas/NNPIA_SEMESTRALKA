package e.the.awesome.springreactcomboapp.controller;

import e.the.awesome.springreactcomboapp.Creator;
import e.the.awesome.springreactcomboapp.model.ApiResponse;
import e.the.awesome.springreactcomboapp.model.AuthToken;
import e.the.awesome.springreactcomboapp.model.LoginUser;
import e.the.awesome.springreactcomboapp.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AuthenticationContollerTest {

        @Autowired
        private AuthenticationController authenticationController;

        @Autowired
        private Creator creator;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Test
        void loginTest() {
            User user = new User();
            user.setUsername("test");
            user.setPassword(bCryptPasswordEncoder.encode("test"));
            creator.saveEntity(user);

            LoginUser loginUser = new LoginUser();
            loginUser.setUsername("test");
            loginUser.setPassword("test");

            ApiResponse<AuthToken> tokenApiResponse = authenticationController.generateToken(loginUser);
            AuthToken responseToken = (AuthToken) tokenApiResponse.getResult();

            Assert.assertEquals(responseToken.getUsername(), "test");
            Assert.assertThat(tokenApiResponse.getStatus(), is(200));
        }

    }


