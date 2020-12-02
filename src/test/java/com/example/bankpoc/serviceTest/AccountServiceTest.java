package com.example.bankpoc.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.example.bankpoc.base.BankBaseTest;
import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.exception.NotFoundException;
import com.example.bankpoc.models.entity.Account;
import com.example.bankpoc.repository.AccountRepository;
import com.example.bankpoc.service.implement.AccountServiceImpl;
import static org.mockito.Mockito.when;

public class AccountServiceTest extends BankBaseTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long id;
    private Account account1;
    private Optional<Account> accountOptional;

    @Before
    public void setUp() {
        id = 1L;
        account1 = new Account(LocalDateTime.now());
        account1.setId(1L);
        account1.setCreatedAt(LocalDateTime.now());
        accountOptional = Optional.of(account1);
    }

    @Test
    public void findByIdTest_WhenIdValidReturnsAnAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(accountOptional);
        Account account = accountService.findById(1L);
        assertNotNull(account);
    }

    @Test
    public void findByIdTest_WhenIdIsInvalidItReturnsNotFoundException() {
        Optional<Account> accountOptional1;
        accountOptional1 = Optional.ofNullable(null);
        when(accountRepository.findById(anyLong())).thenReturn(accountOptional1);
        exception.expect(NotFoundException.class);
        exception.expectMessage("Conta Inexistente");
        accountService.findById(1L);
    }

    @Test
    public void createTest_WhenValuesArePassedCreateAnAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account1);
        Account account = accountService.create(account1);
        assertNotNull(account);
    }

    @Test
    public void updateTest_WhenValuesArePassedUpdateAnAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account1);
        Account account = accountService.update(account1);
        assertEquals(account1.getId(), account.getId());
        assertEquals(account1.getCreatedAt(), account.getCreatedAt());
        assertEquals(account1.getBalance(), account.getBalance(),0.0);
        assertEquals(account1.toString(), account.toString());
    }

    @Test
    public void checkIfAccountExists_WhenAccountIsInvalidItReturnsNotFoundException() {
        Account account = null;
        exception.expect(NotFoundException.class);
        exception.expectMessage("Conta Inexistente");
        accountService.checkIfAccountExists(account);
    }

    @Test
    public void checkIfAccountExistsTest_WhenAccountIsValid() {
        accountService.checkIfAccountExists(account1);
        assertEquals(1,1);
    }
}
