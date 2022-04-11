package com.himanshu.multistepform.viewmodel


import android.text.InputFilter
import android.text.Spanned
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    var button : String = ""
    var department : String = ""
    var email : String = ""
    var phone : String = ""
    var firstName : String = ""
    var lastName : String = ""
    var companyName : String = ""
    var jobTitle : String = ""

    var labelLiveData = MutableLiveData("")
    val labels : LiveData<String>
        get() = labelLiveData

    fun checkAlphabets(): Array<out InputFilter> {
        val reges = Regex("^[a-zA-Z ]+$")
        val check = arrayOf<InputFilter>(
            object : InputFilter {
                override fun filter(
                    cs: CharSequence, start: Int,
                    end: Int, spanned: Spanned?, dStart: Int, dEnd: Int,
                ): CharSequence {
                    if (cs == "") { // for backspace
                        return cs
                    }
                    return if (cs.toString().matches(reges)) {
                        cs
                    } else ""
                }
            }
        )
        return check
    }

}