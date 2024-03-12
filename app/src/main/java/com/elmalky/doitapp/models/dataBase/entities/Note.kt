package com.elmalky.doitapp.models.dataBase.entities

import java.util.Date

data class Note(
    val title: String? = null,
    val content: String? = null,
    val date: Date
)
