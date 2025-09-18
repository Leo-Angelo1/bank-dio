package br.com.banco.repository;


import br.com.banco.exception.AccountNotFoundException;
import br.com.banco.model.AccountWallet;

import java.util.List;

import static br.com.banco.repository.CommonsRepository.checkFoundsForTransaction;

public class AccountRepository {

    private List<AccountWallet> accounts;

    public AccountWallet create(final List<String> pix, final long initialFounds){
        var newAccount= new AccountWallet(initialFounds, pix);
        accounts.add(newAccount);
        return newAccount;
    }

    public void deposit(final String pix, final long foundsAmount){
        var target = findByPix(pix);
        target.addMoney(foundsAmount, "deposito");
    }

    public long withdraw(final String pix, final long amount){
        var source = findByPix(pix);
        checkFoundsForTransaction(source, amount);
        source.reduceMoney(amount);
        return amount;
    }

    public void transferMoney(final String sourcePix, final String targetPix, final long amount){
        var source = findByPix(sourcePix);
        checkFoundsForTransaction(source, amount);
        var target = findByPix(targetPix);
        var massage = "pix enviado de '" + sourcePix + "' para '" + targetPix + "'";
        target.addMoney(source.reduceMoney(amount), source.getService(), massage);
    }

    public AccountWallet findByPix(final String pix){
        return accounts.stream()
                .filter(a -> a.getPix().contains(pix))
                .findFirst()
                .orElseThrow(()-> new AccountNotFoundException("A chave pix '" + pix + "' é inexistente."));
    }

    public List<AccountWallet> list(){
        return this.accounts;
    }
}
