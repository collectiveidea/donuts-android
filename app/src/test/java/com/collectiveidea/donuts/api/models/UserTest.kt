package com.collectiveidea.donuts.api.models

import com.squareup.moshi.Moshi
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.expect

class UserTest {
  val userJson = """
  {
     "id": "63db9251-9c45-41ca-92d6-15e84ebea5b3",
     "github_login": "vgonda",
     "name": "Victoria Gonda",
     "display_name": "Victoria"
  }
  """

  val listJson = """
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

  val moshi = Moshi.Builder().build()

  @Test
  fun fromJson_isValidUser() {
    val jsonAdapter = moshi.adapter(User::class.java)

    val user = jsonAdapter.fromJson(userJson)

    expect("63db9251-9c45-41ca-92d6-15e84ebea5b3") { user.id }
    expect("vgonda") { user.github_login }
    expect("Victoria Gonda") { user.name }
    expect("Victoria") { user.display_name }
  }

  @Test
  fun fromJson_isUserList() {
    val jsonAdapter = moshi.adapter(Array<User>::class.java)

    val users = jsonAdapter.fromJson(listJson)

    assertEquals(2, users.size)
  }
}

