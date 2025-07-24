package org.mk.hbtracker.services

import org.mk.hbtracker.AccountRepository
import org.mk.hbtracker.UserRepository
import org.mk.hbtracker.domain.Account
import org.mk.hbtracker.domain.User
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

@Service
class AccountService(val repository: AccountRepository) {
    fun getAccountByEmail(email: String): Account = repository.findByEmail(email)
    fun createAccount(account: Account): Account = repository.save<Account>(account)
    fun deleteAccount(id: Long) = repository.deleteById(id)
}