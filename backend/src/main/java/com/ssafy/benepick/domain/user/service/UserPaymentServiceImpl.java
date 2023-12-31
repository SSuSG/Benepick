package com.ssafy.benepick.domain.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.benepick.domain.user.entity.UserCard;
import com.ssafy.benepick.domain.user.entity.UserPayment;
import com.ssafy.benepick.domain.user.repository.UserPaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	private final UserPaymentRepository userPaymentRepository;

	@Override
	public List<UserPayment> getUserPaymentListByUserCardAndDate(Long myDataCardId , int year , int month) {
		return userPaymentRepository.findByUserCardIdAndMonth(myDataCardId , year , month);
	}


}
