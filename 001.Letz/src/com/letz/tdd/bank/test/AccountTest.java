package com.letz.tdd.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.letz.tdd.bank.main.Account;

public class AccountTest {

    private Account account;

    @Test
    public void testAccount() throws Exception {
        setup();

    }

    @Test
    public void testGetBalance() throws Exception {
        setup();
        if (account.getBlance() != 10000) {
            fail("getBlance () ==>>" + account.getBlance());
        }
        account = new Account(1000);
        if (account.getBlance() != 1000) {
            fail();
        }
        account = new Account(0);
        if (account.getBlance() != 0) {
            fail();
        }
    }

    @Test
    public void testDeposit() throws Exception {
        setup();
        account.deposit(1000);
        assertEquals(11000, account.getBlance());
    }

    @Before
    public void setup() {
        account = new Account(10000);
    }

    @Test
    public void testWithdraw() throws Exception {
        setup();
        account.withdraw(1000);
        assertEquals(9000, account.getBlance());
    }
}
