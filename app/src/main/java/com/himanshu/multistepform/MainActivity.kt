package com.himanshu.multistepform


import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.himanshu.multistepform.databinding.ActivityMainBinding
import com.himanshu.multistepform.viewmodel.ActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var model: ActivityViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: ActivityMainBinding
    private var label : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(ActivityViewModel::class.java)

        progressBar = ProgressBar(this)


        progressBar.max=100
    }

    override fun onResume() {
        super.onResume()
        model.labels.observe(this) {
            label = it
            if (label == "fragment_first") {
                binding.progressBar.progress = 17
            } else if (label == "fragment_second") {
                binding.progressBar.progress = 34
            } else if (label == "fragment_third") {
                binding.progressBar.progress = 51
            } else if (label == "fragment_fourth") {
                binding.progressBar.progress = 68
            } else if (label == "fragment_fifth") {
                binding.progressBar.progress = 85
            }
        }


    }
}