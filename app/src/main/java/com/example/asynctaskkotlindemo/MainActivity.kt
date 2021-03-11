package com.example.asynctaskkotlindemo

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.example.asynctaskkotlindemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var job: Job

    //    private lateinit var myTask: MyTask
    private val updateText = 1
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            //这里可以对UI进行操作
            when (msg.what) {
                updateText -> mBinding.textView.text = "加载中..."
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var myTask = MyTask()

        mBinding.buttonStart.setOnClickListener {
//           thread {
//               val msg = Message()
//               msg.what = updateText
//               handler.sendMessage(msg)
//           }

//            mBinding.buttonStart.isEnabled = false
            if (mBinding.buttonStart.text == "暂停加载") {
                mBinding.buttonStart.text = "继续加载"
                job.cancel()
            } else {
                mBinding.buttonStart.text = "暂停加载"
                job= Job()
                CoroutineScope(job).launch {
                    
                }
            }
            mBinding.buttonCancel.isEnabled = true
        }
        mBinding.buttonCancel.setOnClickListener {
            mBinding.buttonStart.isEnabled = true
            mBinding.buttonCancel.isEnabled = false
            myTask.cancel(true)
            mBinding.textView.text = "已取消加载"
            mBinding.progressBar.progress = 0
            mBinding.buttonStart.text = "开始加载"

        }
    }

    inner class MyTask : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            mBinding.textView.text = "加载中..."
        }

        override fun doInBackground(vararg params: String?): String? {
            //执行耗时操作
            val i = mBinding.progressBar.progress
//            var count = if (i == 100) 0 else 1
            var count = if (i == 100) {
                0
            } else {
                i
            }
            val length = 1
            while (count < 100) {
                count += length
                //通知进度条更新
                publishProgress(count)
                if (isCancelled) {
                    return null
                } else {
                    Thread.sleep(50)
                }
            }
            return "加载完成"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val value = values[0]
            if (value != null) {
                mBinding.progressBar.progress = value
                //更新进度文字
                mBinding.textView.text = "加载中...${value}%"
            }
        }

        override fun onPostExecute(result: String?) {
            mBinding.textView.text = result
            //使相关按钮不可用
            mBinding.buttonStart.isEnabled = true
            mBinding.buttonStart.text = "重新加载"
            mBinding.buttonCancel.isEnabled = false
        }

        override fun onCancelled() {

        }
    }
}