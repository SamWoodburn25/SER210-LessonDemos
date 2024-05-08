package edu.quinnipiac.ser210.lesson5

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.parcelize.Parcelize

@Parcelize
class Money(val amount: BigDecimal): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

}