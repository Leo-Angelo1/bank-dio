package br.com.banco.model;

import lombok.Getter;

import java.util.List;

import static br.com.banco.model.BankService.ACCOUNT;

@Getter
public class AccountWallet extends Wallet {

    public final List<String> pix;

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long amount, final List<String> pix){
        super(ACCOUNT);
        this.pix = pix;
        addMoney(amount, "valor de criação da conta");
    }

    public void addMoney(final Long amount, final String description) {
       var money = generateMoney(amount, description);
        this.money.addAll(money);
    }
}

