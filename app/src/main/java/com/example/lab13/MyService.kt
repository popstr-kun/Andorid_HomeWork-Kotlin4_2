package com.example.lab13

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*

class MyService : Service() {
    private var channel = ""

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let {
            channel = it.getString("channel", "")
        }

        println("test")
        println(channel)

        broadcast(
            when(channel) {
                "music" -> "歡迎來到音樂頻道"
                "new"   -> "歡迎來到新聞頻道"
                "sport" -> "歡迎來到體育頻道"
                else    -> "頻道錯誤"
            }
        )

        GlobalScope.launch {
            delay(3000)
            broadcast(
                when(channel) {
                    "music" -> "即將播放本月 TOP10 音樂"
                    "new"   -> "即將為您提供獨家新聞"
                    "sport" -> "即將播報本週 NBA 賽事"
                    else    -> "頻道錯誤"
                }
            )
            println("test2")
            println(channel)
        }


        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null
    private fun broadcast(msg: String) = sendBroadcast(Intent(channel).putExtra("msg", msg))
}