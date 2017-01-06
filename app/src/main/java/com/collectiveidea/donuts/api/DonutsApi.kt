package com.collectiveidea.donuts.api

import com.collectiveidea.donuts.api.models.User
import io.reactivex.Observable
import retrofit2.http.GET

interface DonutsApi {
  @GET("/api/v1/claims/today")
  fun getTodayClaims(): Observable<List<User>>
}
