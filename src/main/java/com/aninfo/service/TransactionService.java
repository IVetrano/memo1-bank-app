package com.aninfo.service;

import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) {
        TransactionType transactionType = transaction.getTransactionType();
        Double transactionAmount = transaction.getAmount();
        Long accountCbu = transaction.getAccountCbu();
        if (transactionType == TransactionType.WITHDRAW){
            accountService.withdraw(accountCbu, transactionAmount);
        }
        if (transactionType == TransactionType.DEPOSIT){
            accountService.deposit(accountCbu, transactionAmount);
        }

        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findByTransactionId(Long transactionId) {
        return transactionRepository.findTransactionByTransactionId(transactionId);
    }

    public Optional<Collection<Transaction>> findByAccountCbu(Long accountCbu){
        Collection<Transaction> allTransactions = transactionRepository.findAll();
        Collection<Transaction> accountTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : allTransactions){
            if (transaction.isThisYourAccountCbu(accountCbu)){
                accountTransactions.add(transaction);
            }
        }
        return Optional.of(accountTransactions);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

}
