package com.himanshu.multistepform.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.himanshu.multistepform.viewmodel.ActivityViewModel
import com.himanshu.multistepform.databinding.DataPopupBinding
import com.himanshu.multistepform.databinding.FragmentFifthBinding
import timber.log.Timber


class FifthFragment : Fragment() {
    private lateinit var binding: FragmentFifthBinding
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var model : ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        binding.companyName.filters=model.checkAlphabets()
        binding.jobTitle.filters=model.checkAlphabets()

        val navController : NavController = findNavController()
        val label : String= navController.currentBackStackEntry?.destination?.label.toString()
        model.labelLiveData.value=label

        binding.requestDemo.setOnClickListener {
            checkAllFields()
            model.companyName=binding.companyName.text.toString()
            model.jobTitle=binding.jobTitle.text.toString()

            Timber.d("Company Name: ${model.companyName}")
            Timber.d("Job Title : ${model.jobTitle}")

            binding.companyName.text?.clear()
            binding.jobTitle.text?.clear()
        }
    }
    private fun checkAllFields(): Boolean {
        if (binding.jobTitle.length() == 0) {
            binding.jobTitle.error = "This field is required"
            return false
        }
        else if (binding.companyName.length() == 0 ) {
            binding.companyName.error = "This field is required"
            return false
        }
        showDialog()
        return true
    }
    private fun keepDialog(){
        val lp : WindowManager.LayoutParams = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = lp
    }
    private fun showDialog(){

        dialogBuilder = AlertDialog.Builder(requireContext())
        val layoutInflater : LayoutInflater = LayoutInflater.from(requireContext())
       val popupBinding : DataPopupBinding  = DataPopupBinding.inflate(layoutInflater)
        popupBinding.textViewTwo.text=model.button
        popupBinding.textView4.text=model.department
        popupBinding.textView6.text=model.email
        popupBinding.textView8.text=model.phone
        popupBinding.textView10.text=model.firstName
        popupBinding.textView12.text=model.lastName
        popupBinding.textView14.text=model.companyName
        popupBinding.textView16.text=model.jobTitle
        dialogBuilder.setView(popupBinding.root)
        dialog = dialogBuilder.create()
        dialog.show()

        keepDialog()
        popupBinding.closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}