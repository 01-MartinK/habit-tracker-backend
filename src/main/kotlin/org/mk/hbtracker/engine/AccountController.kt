package org.mk.hbtracker.engine

import org.mk.hbtracker.domain.Account
import org.mk.hbtracker.domain.AccountLoginRequest
import org.mk.hbtracker.domain.CreateAccountRequest
import org.mk.hbtracker.domain.User
import org.mk.hbtracker.services.AccountService
import org.mk.hbtracker.services.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val service: AccountService, private val userService: UserService, private val passwordEncoder: PasswordEncoder) {
    @GetMapping("/")
    fun loginIntoAccount(@RequestBody body: AccountLoginRequest): Boolean {
        val account = service.getAccountByEmail(body.email)
        return passwordEncoder.matches(body.password, account.password);
    }

    @PostMapping("/")
    fun createAccount(@RequestBody body: CreateAccountRequest): Account {
        val user = userService.createUser(User(
            username = body.username,
            createdAt = LocalDateTime.now().toString()
        ))

        val password = passwordEncoder.encode(body.password)

        val account: Account = service.createAccount(
            Account(
                email = body.email,
                password = password,
                lastLogin = LocalDateTime.now().toString(),
                createdAt = LocalDateTime.now().toString(),
                userId = user.id!!
            )
        )

        return account;
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable id: Long) = service.deleteAccount(id)
}