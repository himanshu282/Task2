package com.himanshu.multistepform.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.himanshu.multistepform.viewmodel.ActivityViewModel
import com.himanshu.multistepform.R
import com.himanshu.multistepform.databinding.FragmentThirdBinding
import timber.log.Timber


class ThirdFragment : Fragment() {
    private lateinit var binding:FragmentThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        val navController : NavController = findNavController()
        val label : String= navController.currentBackStackEntry?.destination?.label.toString()
        model.labelLiveData.value=label
        binding.next.setOnClickListener {
            if(checkAllFields()) {
                model.email = binding.email.text.toString()
                model.phone = binding.phone.text.toString()
                Navigation.findNavController(view)
                    .navigate(R.id.action_thirdFragment_to_fourthFragment)
            }

            Timber.d("email : ${model.email}")
            Timber.d("phone : ${model.phone}")

            binding.email.text?.clear()
            binding.phone.text?.clear()

        }

    }
    private fun checkAllFields(): Boolean {
        if (binding.email.length() == 0) {
            binding.email.error = "This field is required"
            return false
        }
        else if (binding.phone.length() == 0 || binding.phone.length() >= 10  ) {
            binding.phone.error = "This field is required and less than 10 "
            return false
        }

        return true
    }

}