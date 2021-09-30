package com.example.progressiveoverload.data

import android.os.Parcel
import android.os.Parcelable

data class Exercise(
//    val id: Int,
    val name: String,
    var sets: MutableList<Sets>,
//    var volume: Int,
//    val createdAt: String,
//    var updatedAt: String,
//    val userId: String
) : Parcelable {
    constructor(parcel: Parcel) : this (
        parcel.readString()!!,
        parcel.createTypedArrayList(Sets.CREATOR)!!
    )
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        dest.writeString(name)
        dest.writeList(sets)
    }
    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}
