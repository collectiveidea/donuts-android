package com.collectiveidea.donuts.api.models

import com.squareup.moshi.Json

data class User(
    val id: String,
    @Json(name = "github_login") val githubLogin: String,
    val name: String,
    @Json(name = "display_name") val displayName: String
)
