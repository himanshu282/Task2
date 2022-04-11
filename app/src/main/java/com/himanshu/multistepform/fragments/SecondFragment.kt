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
import com.himanshu.multistepform.databinding.FragmentSecondBinding
import timber.log.Timber


class SecondFragment : Fragment() {
    private lateinit var binding : FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        binding.customerService.setOnClickListener {
            model.department=binding.customerService.text.toString()
            toThirdFragment(view) }
        binding.marketing.setOnClickListener {
            model.department=binding.marketing.text.toString()
            toThirdFragment(view) }
        binding.sales.setOnClickListener {
            model.department=binding.sales.text.toString()
            toThirdFragment(view) }
        Timber.d(model.department)
        val navController : NavController = findNavController()
        val label : String= navController.currentBackStackEntry?.destination?.label.toString()
        model.labelLiveData.value=label
    }

    private fun toThirdFragment(view: View){
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment)
    }


}