package com.gmind.mymovieproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout (
    var kursi : String ? = "",
    var harga : String ? = ""
) : Parcelable