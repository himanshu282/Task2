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
import com.himanshu.multistepform.databinding.FragmentFirstBinding
import timber.log.Timber


class FirstFragment : Fragment() {
    private lateinit var binding : FragmentFirstBinding
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
        binding= FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        binding.generationBots.setOnClickListener{
            model.button = binding.generationBots.text.toString()
            toSecondFragment(view)
        }
        binding.integrations.setOnClickListener {
            model.button = binding.integrations.text.toString()
            toSecondFragment(view) }
        binding.multiStep.setOnClickListener {
            model.button = binding.multiStep.text.toString()
            toSecondFragment(view) }

        Timber.d( "onViewCreated: ${model.button}")

        val navController : NavController = findNavController()
        val label : String= navController.currentBackStackEntry?.destination?.label.toString()
        model.labelLiveData.value=label
        Timber.d( "onViewCreated: $label ")
    }

    private fun toSecondFragment(view: View){
        Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment)

    }

}