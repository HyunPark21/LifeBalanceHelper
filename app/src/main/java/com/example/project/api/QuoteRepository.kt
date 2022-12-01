package com.example.project.api



class quoteRepository(private val api: quoteAPI) {
    suspend fun fetchFact(): quote {
        return api.fetchFact()[0]
    }
}