package com.himanshu.multistepform.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.himanshu.multistepform.viewmodel.ActivityViewModel
import com.himanshu.multistepform.R
import com.himanshu.multistepform.databinding.FragmentFourthBinding
import timber.log.Timber

class FourthFragment : Fragment() {
    private lateinit var binding : FragmentFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        binding.fname.filters=model.checkAlphabets()
        binding.lname.filters=model.checkAlphabets()

        val navController : NavController = findNavController()
        val label : String= navController.currentBackStackEntry?.destination?.label.toString()
        model.labelLiveData.value=label

        binding.next.setOnClickListener {
            if(checkAllFields()) {
                model.firstName=binding.fname.text.toString()
                model.lastName=binding.lname.text.toString()
                Navigation.findNavController(view)
                    .navigate(R.id.action_fourthFragment_to_fifthFragment)
            }
            Timber.d("FirstName : ${model.firstName}")
            Timber.d("LastName : ${model.lastName}")

            binding.fname.text?.clear()
            binding.lname.text?.clear()
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.fname.length() == 0) {
            binding.fname.error = "This field is required"
            return false
        }
        else if (binding.lname.length() == 0 ) {
            binding.lname.error = "This field is required"
            return false
        }

        return true
    }


}