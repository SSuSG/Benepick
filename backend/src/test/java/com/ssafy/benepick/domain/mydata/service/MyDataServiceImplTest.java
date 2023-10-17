package com.ssafy.benepick.domain.mydata.service;

import com.ssafy.benepick.domain.user.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MyDataServiceImplTest {


    @DisplayName("사용자는 이번달 소비내역을 조회 할 수 있다.")
    @Test
    void 이번달_소비내역_조회() {
        // given
        UserCard userCard = createUserCard();
        int payAmount = 10000;
        int benefitAmount = 1500;


        // when

        // then

    }



    private UserCard createUserCard(){
        UserCardCategory3 userCardCategory3 = UserCardCategory3.builder()
                .userCardCategory3Name("스타벅스")
                .build();

        List<UserCardCategory3> userCardCategory3List = new ArrayList<>();
        userCardCategory3List.add(userCardCategory3);

        UserCardCategory2 userCardCategory2 = UserCardCategory2.builder()
                .userCardCategory3List(userCardCategory3List)
                .build();

        List<UserCardCategory2> userCardCategory2List = new ArrayList<>();
        userCardCategory2List.add(userCardCategory2);

        UserCardBenefit userCardBenefit = UserCardBenefit.builder()
                .cardBenefitPerformanceLevel(1L)
                .userCardBenefitDiscountPercent(10)
                .userCardBenefitPerformanceStart(0)
                .userCardBenefitPerformanceEnd(10000)
                .build();

        List<UserCardBenefit> userCardBenefitList = new ArrayList<>();
        userCardBenefitList.add(userCardBenefit);

        UserCardCategory1 userCardCategory1 = UserCardCategory1.builder()
                .userCardCategory1Name("카테고리1")
                .userCardCategory2List(userCardCategory2List)
                .userCardBenefitList(userCardBenefitList)
                .build();

        List<UserCardCategory1> userCardCategory1List = new ArrayList<>();
        userCardCategory1List.add(userCardCategory1);

        return UserCard.builder()
                .userCardCompanyName("test1")
                .userCardName("카드")
                .userCardCategory1List(userCardCategory1List)
                .build();

    }
}