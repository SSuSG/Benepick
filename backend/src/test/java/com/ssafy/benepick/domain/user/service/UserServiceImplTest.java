package com.ssafy.benepick.domain.user.service;

import com.ssafy.benepick.domain.user.dto.request.CreateUserAccountRequestDto;
import com.ssafy.benepick.domain.user.dto.request.LoginRequestDto;
import com.ssafy.benepick.domain.user.entity.User;
import com.ssafy.benepick.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("회원가입 정보를 바탕으로 회원가입을 할수있다.")
    @Test
    void 회원가입(){
        // given
        CreateUserAccountRequestDto createUserAccountRequestDto = CreateUserAccountRequestDto.builder()
                .userName("김싸피")
                .userPhoneNumber("01011112222")
                .userSocialNumber("987654")
                .userGenderAndGenerationCode("1")
                .userSimplePassword("123123")
                .build();

        User user = createUserAccountRequestDto.toUserEntity("userId");
        int userCount = (int)userRepository.count();

        // when
        User saveUser = userRepository.save(user);

        // then
        assertThat((int)userRepository.count()).isEqualTo(userCount + 1);
        assertThat(user.getUserId()).isEqualTo(saveUser.getUserId());
    }

    @DisplayName("간편 비밀번호 바탕으로 로그인을 할 수 있다.")
    @Test
    void 간편로그인(){
        // given
        User user1 = User.builder()
                .userId("user1user1user1")
                .userName("김싸피")
                .userPhoneNumber("01012345678")
                .userSocialNumber("990101")
                .userSimplePassword("123456")
                .userGenderAndGenerationCode("1")
                .isPushActive(false)
                .isAutoLoginActive(false)
                .userCardList(new ArrayList<>())
                .build();

        userRepository.save(user1);

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .userSimplePassword("123456")
                .build();

        // when
        User user = userRepository.findById(user1.getUserId()).get();

        // then
        assertThat(user.getUserSimplePassword()).isEqualTo(loginRequestDto.getUserSimplePassword());
    }

}