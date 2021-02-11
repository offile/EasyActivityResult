package com.github.offile.activityresult.sample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.offile.activityresult.EasyActivityResult
import com.github.offile.activityresult.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val easyActivityResult = EasyActivityResult(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // use ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startActivityForResult.setOnClickListener {
            onClickStartActivityForResult()
        }
        binding.requestPermissions.setOnClickListener {
            onClickRequestPermissions()
        }
    }

    private fun onClickStartActivityForResult() {
        val intent = Intent(this, ResultActivity::class.java)
        easyActivityResult.startActivityForResult(intent){ resultCode, data ->
            when(resultCode){
                RESULT_OK -> {
                    val value = data?.getStringExtra("data")
                    Toast.makeText(this, "result = $value", Toast.LENGTH_LONG).show()
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this, "user cancel", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onClickRequestPermissions() {
        easyActivityResult.requestPermissions(
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
        ){ permissions, grantResults ->
            val isGranted = grantResults.isNotEmpty()
                    && grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if(isGranted){
                Toast.makeText(this, "Get permission successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

}