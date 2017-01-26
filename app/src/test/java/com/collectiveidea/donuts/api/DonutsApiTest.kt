package com.collectiveidea.donuts.api

import com.collectiveidea.donuts.api.models.User
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlin.test.expect

class DonutsApiTest {
  @get:Rule val server = MockWebServer()

  val observer by lazy { TestObserver<List<User>>() }

  val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
  }
  val donutsApi: DonutsApi by lazy { retrofit.create(DonutsApi::class.java) }

  @Test
  fun getTodayClaims_withNoClaims_emitsEmptyList() {
    server.enqueue(MockResponse().setBody("[]"))
    donutsApi.getTodayClaims().subscribe(observer)
    observer.assertNoErrors()
    observer.assertValue(emptyList<User>())
    expect("/api/v1/claims/today") { server.takeRequest().path }
  }

  @Test
  fun getTodayClaims_withClaims_emitsListOfUsers() {
    val claimsJson = """
      [{
         "id": "63db9251-9c45-41ca-92d6-15e84ebea5b3",
         "github_login": "vgonda",
         "name": "Victoria Gonda",
         "display_name": "Victoria"
      },
      {
         "id": "02e54e6e-1501-4634-88b2-b8e10823c19b",
         "github_login": "shekibobo",
         "name": "Josh Kovach",
         "display_name": "Josh"
      }]
    """

    val user1 = User(
        id = "63db9251-9c45-41ca-92d6-15e84ebea5b3",
        githubLogin = "vgonda",
        name = "Victoria Gonda",
        displayName = "Victoria"
    )
    val user2 = User(
        id = "02e54e6e-1501-4634-88b2-b8e10823c19b",
        githubLogin = "shekibobo",
        name = "Josh Kovach",
        displayName = "Josh"
    )

    server.enqueue(MockResponse().setBody(claimsJson))

    donutsApi.getTodayClaims().subscribe(observer)
    observer.assertNoErrors()
    observer.assertValue(listOf(user1, user2))
    expect("/api/v1/claims/today") { server.takeRequest().path }
  }
}

interface DonutsApi {
  @GET("/api/v1/claims/today")
  fun getTodayClaims(): Observable<List<User>>
}

