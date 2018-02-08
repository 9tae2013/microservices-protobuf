package it.me.tae.account.data;
import it.me.tae.account.model.AccountProto.Account;

import java.util.List;
import java.util.stream.Collectors;


public class AccountRepository {

    List<Account> accounts;

    public AccountRepository(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> findAll() {
        return accounts;
    }

    public List<Account> findByCustomer(int customerId) {
        return accounts.stream().filter(it -> it.getCustomerId() == customerId).collect(Collectors.toList());
    }

    public Account findByNumber(String number) {
        return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
    }

}