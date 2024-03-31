package com.elmalky.doitapp.models.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TODO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var task: String,
    val priority: String? = "",
    val priorityColor: String? = "#FFFFFF",
    val state: Boolean = false
)
