package com.example.alejofila.gnomeland.network

import io.reactivex.Single
import retrofit2.http.GET

/**
 *
 */
interface GnomeService{
    @GET("rrafols/mobile_test/master/data.json")
    fun getGnomes(): Single<GnomesResponse>

}