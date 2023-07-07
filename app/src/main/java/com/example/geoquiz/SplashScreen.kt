package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        val executor: ExecutorService = Executors.newFixedThreadPool(1)
//        val task = Runnable {
//            Thread.sleep(5000)
//            // switch to another activity
//        }
//        executor.execute(task)
//        executor.shutdown()
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
            finish()
        }, 3000)
    }
}