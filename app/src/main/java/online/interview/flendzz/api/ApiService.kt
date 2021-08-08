package com.emperor.kotlinexample.api


import online.interview.flendzz.model.UserModelItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getEmployee(): List<UserModelItem>

    @GET("users/{id}")
    suspend fun getEmployeeDetails(
        @Path("id") id: Int
    ): UserModelItem
}