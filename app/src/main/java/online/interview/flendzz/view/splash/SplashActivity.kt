package online.interview.flendzz.view.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import online.interview.flendzz.databinding.ActivitySplashScreenBinding
import online.interview.flendzz.view.employee.UserDetailsActivity

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    var activitySplashBind: ActivitySplashScreenBinding?=null;
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBind=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(activitySplashBind!!.root)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN)

        activityScope.launch {
            delay(3000)
                var intent = Intent(applicationContext, UserDetailsActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}