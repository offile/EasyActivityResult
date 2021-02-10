package com.github.offile.activityresult.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.offile.activityresult.sample.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirm.setOnClickListener {
            val value = binding.input.text.toString()
            val intent = Intent()
            intent.putExtra("data", value)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}