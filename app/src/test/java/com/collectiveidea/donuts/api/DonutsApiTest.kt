package com.collectiveidea.donuts.api

import com.collectiveidea.donuts.api.models.User
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class DonutsApiTest {
  private val observer by lazy { TestObserver<List<User>>() }
  private val  donutsApi by lazy { DonutsApi() }

  @Test
  fun getTodayClaims_withNoClaims_returnsEmptyList() {
    donutsApi.getTodayClaims().subscribe(observer)
    observer.assertNoErrors()
    observer.assertValue(emptyList<User>())
  }

  @Test
  fun getTodayClaims_withClaims_returnsListOfUsers() {
    val user = User(id = "hello", github_login = "hi", name = "Hello World", display_name = "Hello")
    donutsApi.getTodayClaims().subscribe(observer)
    observer.assertNoErrors()
    observer.assertValue(listOf(user))
  }
}

class DonutsApi {
  fun getTodayClaims(): Observable<List<User>> {
    return Observable.just(emptyList<User>())
  }
}

