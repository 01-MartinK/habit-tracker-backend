package org.mk.hbtracker.engine

import org.mk.hbtracker.domain.CreateUserRequest
import org.mk.hbtracker.domain.UpdateUserRequest
import org.mk.hbtracker.domain.User
import org.mk.hbtracker.services.UserService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/users")
public class UserController(private val service: UserService) {
    @GetMapping("/")
    fun getUsers(): List<User> = service.getAllUsers()

    @PostMapping("/")
    fun createUser(@RequestBody body: CreateUserRequest): User = service.createUser(
        User(
            username = body.username,
            createdAt = LocalDateTime.now().toString(),
        )
    )

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody body: UpdateUserRequest): User = service.updateUser(
        id, User(
            id = id,
            username = body.username,
        )
    )

    @DeleteMapping("/{id}")
    fun deleteHabit(@PathVariable id: Long) = service.deleteUser(id)
}