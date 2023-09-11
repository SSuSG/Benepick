package com.ssafy.benepick.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.benepick.domain.mydata.entity.MyDataCard;
import com.ssafy.benepick.domain.mydata.entity.MyDataPayment;
import com.ssafy.benepick.domain.user.entity.User;
import com.ssafy.benepick.domain.user.entity.UserCard;
import com.ssafy.benepick.domain.user.entity.UserPayment;
import com.ssafy.benepick.domain.user.repository.UserCardRepository;
import com.ssafy.benepick.domain.user.repository.UserPaymentRepository;
import com.ssafy.benepick.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCardServiceImpl implements  UserCardService{

	private final UserCardRepository userCardRepository;
	private final UserRepository userRepository;
	private final UserPaymentRepository userPaymentRepository;

	@Override
	@Transactional(transactionManager = "benepickTransactionManager")
	public void linkUserCardAndUserPaymentByMyDataCard(List<MyDataCard> myDataCardList) {
		log.info("UserCardServiceImpl_linkUserCardAndUserPaymentByMyDataCard || 마이데이터 유저 카드 데이터를 유저 카드데이터에 연동");
		User user = userRepository.findById(myDataCardList.get(0).getMyDataUser().getMyDataUserId()).get();

		myDataCardList.stream().forEach(myDataCard -> {
			UserCard userCard = myDataCardToUserCard(myDataCard, user);
			userCardRepository.save(userCard);

			List<UserPayment> userCardPaymentList = myDataCard.getMyDataPaymentList().stream()
				.map(myDataPayment -> myDataPaymentToUserPayment(myDataPayment,userCard))
				.collect(Collectors.toList());
			userPaymentRepository.saveAll(userCardPaymentList);
		});
	}

	@Override
	public UserCard myDataCardToUserCard(MyDataCard myDataCard,User user) {
		return UserCard.builder()
			.user(user)
			.userCardCompanyName(myDataCard.getCard().getCardCompany().getCardCompanyName())
			.userCardSerialNumber(myDataCard.getMyDataCardId())
			.userCardCode(myDataCard.getCard().getCardCode())
			.userCardName(myDataCard.getCard().getCardName())
			.userCardExpirationDate(myDataCard.getMyDataCardExpirationDate())
			.userCardImgUrl(myDataCard.getCard().getCardImgUrl())
			.userCardCompanyImgUrl(myDataCard.getCard().getCardCompany().getCardCompanyImgUrl())
			.userCardCurrentPerformance(0)
			.userCardPrevPerformance(0)
			.build();
	}

	@Override
	public UserPayment myDataPaymentToUserPayment(MyDataPayment myDataPayment , UserCard userCard) {
		return UserPayment.builder()
			.userCard(userCard)
			.userPaymentCategory1(myDataPayment.getMyDataPaymentCategory())
			.userPaymentCategory2(myDataPayment.getMyDataPaymentCategory2())
			.userPaymentDateTime(myDataPayment.getMyDataPaymentDate())
			.userPaymentAmount(myDataPayment.getMyDataPaymentAmount())
			.userPaymentMerchantInfo(myDataPayment.getMyDataPaymentMerchantName())
			.userPaymentReceivedBenefitAmount(myDataPayment.getMyDataPaymentReceivedBenefitAmount())
			.userPaymentCardCode(myDataPayment.getMyDataPaymentCardCode())
			.build();
	}
}