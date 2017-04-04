package com.collectiveidea.donuts.api.presenters

import com.collectiveidea.donuts.api.DonutsApi
import com.collectiveidea.donuts.api.models.User
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

class ClaimsPresenterTest {
  @Test
  fun onAttach_togglesLoadingWhileFetchingClaimsFromServer() {
    val view = mock<ClaimsPresenter.View>()

    val api = mock<DonutsApi>()
    whenever(api.getTodayClaims()).thenReturn(Observable.just(listOf()))

    val presenter = ClaimsPresenter(view, api)

    presenter.onAttach()

    val inOrder = inOrder(view, api)
    inOrder.verify(view).showLoading(true)
    inOrder.verify(api).getTodayClaims()
    inOrder.verify(view).showLoading(false)
    inOrder.verify(view).showNoUsers()
  }

  @Test
  fun onAttach_showsUsersFromServer() {
    val view = mock<ClaimsPresenter.View>()

    val user = User(
        id = "id",
        name = "Josh Kovach",
        displayName = "Josh",
        githubLogin = "shekibobo"
    )
    val api = mock<DonutsApi>()
    whenever(api.getTodayClaims()).thenReturn(Observable.just(listOf(user)))

    val presenter = ClaimsPresenter(view, api)

    presenter.onAttach()

    val inOrder = inOrder(view, api)
    inOrder.verify(view).showLoading(true)
    inOrder.verify(api).getTodayClaims()
    inOrder.verify(view).showLoading(false)
    inOrder.verify(view).showUsers(listOf(user))
  }
}

class ClaimsPresenter(private val view: View, private val api: DonutsApi) {
  interface View {
    fun  showLoading(isLoading: Boolean)
    fun showNoUsers()
    fun showUsers(users: List<User>)
  }

  fun onAttach() {
    view.showLoading(true)
    api.getTodayClaims()
        .subscribe({ users ->
          view.showLoading(false)
          if (users.isEmpty()) {
            view.showNoUsers()
          } else {
            view.showUsers(users)
          }
        })
  }
}


