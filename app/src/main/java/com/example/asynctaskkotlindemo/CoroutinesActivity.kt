package com.example.asynctaskkotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.asynctaskkotlindemo.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityCoroutinesBinding
    private var job = Job()
    private val scope = CoroutineScope(job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.startButton.setOnClickListener {
            job= Job()
            CoroutineScope(job).launch {
                for (i in 5 downTo 1) {
                    withContext(Dispatchers.Main){
                        mBinding.timeTextView.text = i.toString()
                    }
                    delay(1000)
                }
            }
        }
        mBinding.stopButton.setOnClickListener {
            job.cancel()
        }
    }
}
