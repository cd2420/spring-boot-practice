package com.example.security.account;

public class AccountContext {

    private static final ThreadLocal<Account> ACCONT_THREAD_LOCAL
            = new ThreadLocal<>();

    public static void setAccount(Account account) {
        ACCONT_THREAD_LOCAL.set(account);
    }

    public static Account getAccount() {
        return ACCONT_THREAD_LOCAL.get();
    }
}
