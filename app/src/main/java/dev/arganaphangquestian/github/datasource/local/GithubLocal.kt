package dev.arganaphangquestian.github.datasource.local

import dev.arganaphangquestian.github.data.dao.GithubDao
import dev.arganaphangquestian.github.data.entity.User
import javax.inject.Inject

class GithubLocal @Inject constructor(private val dao: GithubDao) {
    fun getAll() = dao.findAll()

    fun delete(user: User) {
        dao.delete(user)
    }

    fun save(user: User) {
        dao.save(user)
    }
}