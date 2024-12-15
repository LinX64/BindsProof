package com.client.bindsproof

import javax.inject.Inject

interface NewsRepository {
    fun getData(): String
}

class NewsRepositoryImpl @Inject constructor() : NewsRepository {
    override fun getData(): String = "Sample data"
}
