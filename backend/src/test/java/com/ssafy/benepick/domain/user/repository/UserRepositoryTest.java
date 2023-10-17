package com.ssafy.benepick.domain.user.repository;

import com.ssafy.benepick.domain.user.entity.User;
import com.ssafy.benepick.global.exception.NotExistUserCiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("이름,핸드폰 번호,생년월일이 동일한 유저를 판단해준다.")
    @Test
    void existsByUserNameAndUserPhoneNumberAndUserSocialNumber() {
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

        User user2 = User.builder()
                .userId("user2user2user2")
                .userName("김싸피")
                .userPhoneNumber("01012345678")
                .userSocialNumber("990101")
                .userSimplePassword("654321")
                .userGenderAndGenerationCode("2")
                .isPushActive(false)
                .isAutoLoginActive(false)
                .userCardList(new ArrayList<>())
                .build();

        // when // then
        assertThat(userRepository.existsByUserNameAndUserPhoneNumberAndUserSocialNumber(user2.getUserName() , user2.getUserPhoneNumber() , user2.getUserSocialNumber()))
                .isTrue();
    }

    @DisplayName("존재하지 않는 CI로 유저를 찾을시 예외가 발생한다.")
    @Test
    void findByNotExistIdThrowException(){
        // given
        String userCi = "존재하지않을Ci";

        // when // then
        assertThatThrownBy(() -> userRepository.findById(userCi).orElseThrow(NotExistUserCiException::new))
                .isInstanceOf(NotExistUserCiException.class);

    }

    @DisplayName("사용자는 회원탈퇴를 할 수 있다.")
    @Test
    void withDrawal() {
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

        User save = userRepository.save(user1);
        int userCount = (int)userRepository.count();

        // when
        userRepository.delete(save);

        // then
        assertThat((int)userRepository.count()).isEqualTo(userCount-1);
        assertThatThrownBy(() -> userRepository.findById(save.getUserId()).orElseThrow(NotExistUserCiException::new))
                .isInstanceOf(NotExistUserCiException.class);

    }
}