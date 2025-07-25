package com.loanmanagement.loansystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanmanagement.loansystem.Entity.LoanApplication;
import com.loanmanagement.loansystem.Entity.User;
import com.loanmanagement.loansystem.Exception.NoLoanHistoryFoundException;
import com.loanmanagement.loansystem.Repository.LoanAppRepository;
import com.loanmanagement.loansystem.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanService {
	@Autowired
	private LoanAppRepository loanAppRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
    public List<LoanApplication> findByLoanappid(String username)
    {
    	Optional<User> user= userRepository.findByUsername(username);
    	
    	List<LoanApplication> loanApplicationList = loanAppRepository.findByUserId(user.get().getId());
    	
    	if (loanApplicationList.isEmpty()) {
            throw new NoLoanHistoryFoundException("No loan history found for user : " + username);
        }
    	 	
		return loanApplicationList;
    }
}