package com.example.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import com.example.activitylifecycle.databinding.ActivityMainBinding
import com.example.activitylifecycle.fragments.SampleFragment

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var binding: ActivityMainBinding? = null

    val activityResultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                Log.e(TAG, ": ${data?.getStringExtra("Data")}")
            }
        }

    val customContract = registerForActivityResult(SecondActivity.CustomContract()) {
        Log.e(TAG, ":customContract $it ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Log.e(TAG, "onCreate:")
//        savedInstanceState?.let {
//            Log.e(TAG, "onCreate: savedInstanceState $savedInstanceState")
//        } ?: run {
//            Log.e(TAG, "onCreate: savedInstanceState is null")
//        }
        addFragmentUsingAddMethod()
        binding?.button?.setOnClickListener {
            // startActivity(Intent(this, SecondActivity::class.java))
            // addFragmentUsingReplaceMethod()
            activityResultContract.launch(Intent(this, SecondActivity::class.java))
        }

        binding?.buttonCustom?.setOnClickListener {
            customContract.launch(
                SecondActivity.InputClass(
                    "Hey We launched from custom",
                    Activity.RESULT_OK
                )
            )
        }

    }

    private fun addFragmentUsingAddMethod() {
        //withod back stack
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.FragmentContainerView, SampleFragment.newInstance("", ""))
            addToBackStack("Add")
        }
    }

    private fun addFragmentUsingReplaceMethod() {
        //withod back stack
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.FragmentContainerView, SampleFragment.newInstance("", ""))
            addToBackStack("Replace")
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(TAG, "onRestoreInstanceState: savedInstanceState")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }


    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }
}