package br.com.banco.repository;

import br.com.banco.exception.NoFoundsEnoughException;
import br.com.banco.model.Money;
import br.com.banco.model.MoneyAudit;
import br.com.banco.model.Wallet;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static br.com.banco.model.BankService.ACCOUNT;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CommonsRepository {

    public static void checkFundsForTransaction(final Wallet source, final long amount){
        if(source.getFunds() < amount){
            throw new NoFoundsEnoughException("Saldo insuficiente para essa transação");
        }
    }

    public static List<Money> generateMoney(final UUID transactionId, final long founds, final String description){
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(founds).toList();
    }
}
