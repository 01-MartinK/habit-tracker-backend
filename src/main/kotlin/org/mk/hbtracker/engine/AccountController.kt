package org.mk.hbtracker.engine

import org.mk.hbtracker.domain.Account
import org.mk.hbtracker.domain.CreateAccountRequest
import org.mk.hbtracker.domain.CreateUserRequest
import org.mk.hbtracker.domain.UpdateUserRequest
import org.mk.hbtracker.domain.User
import org.mk.hbtracker.services.AccountService
import org.mk.hbtracker.services.UserService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val service: AccountService, private val userService: UserService) {
    @GetMapping("/")
    fun getUsers(@RequestBody body: String): Account = service.getAccountByEmail(body)

    @PostMapping("/")
    fun createUser(@RequestBody body: CreateAccountRequest): Account {
        val user = userService.createUser(User(
            username = body.username,
            createdAt = LocalDateTime.now().toString()
        ))

        val account: Account = service.createAccount(
            Account(
                email = body.email,
                password = body.password,
                lastLogin = LocalDateTime.now().toString(),
                createdAt = LocalDateTime.now().toString(),
                userId = user.id!!
            )
        )

        return account;
    }

    @DeleteMapping("/{id}")
    fun deleteHabit(@PathVariable id: Long) = service.deleteAccount(id)
}