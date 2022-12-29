package com.example.lab13

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            println("receive")
            intent.extras?.let {
                println("testing"+it.getString("msg"))
                binding.tvMsg.text = "${it.getString("msg")}"
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMusic.setOnClickListener{
            register("music")
        }
        binding.btnNew.setOnClickListener{
            register("new")
        }
        binding.btnSport.setOnClickListener{
            register("sport")
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        println("isDestroy")
        super.onDestroy()
    }

    private fun register(channel:String){
        val intentFilter = IntentFilter(channel)
        registerReceiver(receiver,intentFilter)

        val i = Intent(this, MyService::class.java)
        startService(i.putExtra("channel", channel))
    }
}