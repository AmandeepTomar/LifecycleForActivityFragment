package com.example.activitylifecycle

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.example.activitylifecycle.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private var _binding: ActivitySecondBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Log.e(TAG, "onCreate:")

        Log.e(TAG, "onCreate: ${intent.getStringExtra("Data")} and ${intent.getIntExtra("Code",0)}", )
        savedInstanceState?.let {
            Log.e(TAG, "onCreate: savedInstanceState $savedInstanceState")
        } ?: run {
            Log.e(TAG, "onCreate: savedInstanceState is null")
        }

        binding?.button?.setOnClickListener {
            val intentData = Intent().apply {
                putExtra("Data", "From Result Screen")
            }
            setResult(Activity.RESULT_OK, intentData)
            finish()
        }

        binding?.buttonCustom?.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("Success", "Success fully we have completed")
            })
            finish()
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

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        Log.e(TAG, "onRestoreInstanceState: savedInstanceState, persistentState")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }


    class CustomContract : ActivityResultContract<InputClass, OutPutClass>() {
        override fun createIntent(context: Context, input: InputClass): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra("Data", input.string)
                putExtra("Code", input.code)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?) = OutPutClass(
            message = intent?.getStringExtra("Success").orEmpty(),
            value = "",
            code = resultCode
        )

    }

    data class InputClass(val string: String, val code: Int)
    data class OutPutClass(val message: String, val value: String = "", val code: Int)


}