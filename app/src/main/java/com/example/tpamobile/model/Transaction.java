package com.example.tpamobile.model;

import com.google.type.DateTime;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

public class Transaction implements Serializable {
    private String TransactionID, TransactionNote;
    private Integer TransactionAmount;
    private Category TransactionCategory;
    private Wallet TransactionWallet;
    private Date TransactionDate;
    private DateTime createdAt;

    public Transaction(String transactionID, String transactionNote, Integer transactionAmount, Category transactionCategory, Wallet transactionWallet, Date transactionDate) {
        TransactionID = transactionID;
        TransactionNote = transactionNote;
        TransactionAmount = transactionAmount;
        TransactionCategory = transactionCategory;
        TransactionWallet = transactionWallet;
        TransactionDate = transactionDate;
    }

    public Transaction() {
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getTransactionNote() {
        return TransactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        TransactionNote = transactionNote;
    }

    public Integer getTransactionAmount() {
        return TransactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        TransactionAmount = transactionAmount;
    }

    public Category getTransactionCategory() {
        return TransactionCategory;
    }

    public void setTransactionCategory(Category transactionCategory) {
        TransactionCategory = transactionCategory;
    }

    public Wallet getTransactionWallet() {
        return TransactionWallet;
    }

    public void setTransactionWallet(Wallet transactionWallet) {
        TransactionWallet = transactionWallet;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public String formatRupiah(){
        DecimalFormat IndExcRate = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        IndExcRate.setDecimalFormatSymbols(formatRp);
        return IndExcRate.format(this.TransactionAmount);
    }
}
