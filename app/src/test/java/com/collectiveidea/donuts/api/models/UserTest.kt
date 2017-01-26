package com.collectiveidea.donuts.api.models

import com.squareup.moshi.Moshi
import org.junit.Test
import kotlin.test.expect

class UserTest {
  val json = """
  {
     "id": "63db9251-9c45-41ca-92d6-15e84ebea5b3",
     "github_login": "vgonda",
     "name": "Victoria Gonda",
     "display_name": "Victoria"
  }
  """
  val moshi = Moshi.Builder().build()

  @Test
  fun fromJson_isValidUser() {
    val jsonAdapter = moshi.adapter(User::class.java)

    val user = jsonAdapter.fromJson(json)

    expect("63db9251-9c45-41ca-92d6-15e84ebea5b3") { user.id }
    expect("vgonda") { user.githubLogin }
    expect("Victoria Gonda") { user.name }
    expect("Victoria") { user.displayName }
  }
}

