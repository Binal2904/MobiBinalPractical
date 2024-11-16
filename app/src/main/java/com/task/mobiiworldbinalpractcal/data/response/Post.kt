package com.task.mobiiworldbinalpractcal.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) // Auto-generate the ID
    val uId: Int = 0,
    val id: Int = 0,
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    var isBookMark: Boolean = false,
)