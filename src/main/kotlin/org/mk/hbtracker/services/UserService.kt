package org.mk.hbtracker.services

import org.mk.hbtracker.UserRepository
import org.mk.hbtracker.domain.User
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {
    fun getAllUsers(): List<User> = repository.findAll()
    fun createUser(user: User): User = repository.save<User>(user)
    fun updateUser(id: Long, user: User): User {
        return if (repository.existsById(id)) {
            repository.save<User>(
                User(
                    id = id,
                    username = user.username,
                    createdAt = user.createdAt,
                )
            )
        } else throw ChangeSetPersister.NotFoundException()
    }

    fun deleteUser(id: Long) = repository.deleteById(id)
}