package dev.arganaphangquestian.github.datasource.local

import dev.arganaphangquestian.github.data.dao.GithubDao
import dev.arganaphangquestian.github.data.entity.User

class GithubLocal(private val dao: GithubDao) {
    fun getAll() = dao.findAll()

    fun delete(user: User) {
        dao.delete(user)
    }

    fun save(user: User) {
        dao.save(user)
    }
}