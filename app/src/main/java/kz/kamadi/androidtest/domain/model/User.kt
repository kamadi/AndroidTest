package kz.kamadi.androidtest.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String
) : Parcelable