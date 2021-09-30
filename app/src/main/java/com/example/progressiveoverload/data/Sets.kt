package com.example.progressiveoverload.data

import android.os.Parcel
import android.os.Parcelable

data class Sets(
    val kg: Double,
    val n: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt()
    )
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Sets> {
        override fun createFromParcel(source: Parcel): Sets {
            return Sets(source)
        }

        override fun newArray(size: Int): Array<Sets?> {
            return arrayOfNulls(size)
        }
    }
}
