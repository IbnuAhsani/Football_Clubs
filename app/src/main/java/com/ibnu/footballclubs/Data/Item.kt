package com.ibnu.footballclubs.Data
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val name: String?, val image: Int?, val details: String?) : Parcelable