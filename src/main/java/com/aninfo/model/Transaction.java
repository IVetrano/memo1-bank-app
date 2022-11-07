package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private Double amount;
    private TransactionType transactionType;
    private Long accountCbu;

    public Transaction(){
    }

    public Transaction(TransactionType transactionType, Double transactionAmount, Long accountCbu) {
        this.transactionType = transactionType;
        this.amount = transactionAmount;
        this.accountCbu = accountCbu;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Double getAmount(){
        return amount;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }

    public Long getAccountCbu(){
        return accountCbu;
    }
    public Boolean isThisYourAccountCbu(Long cbu){
        return accountCbu.equals(cbu);
    }
}
