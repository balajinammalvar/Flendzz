
package online.interview.flendzz.view.employee

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import online.interview.flendzz.R
import online.interview.flendzz.databinding.ActivityEmployeeDetailBinding
import online.interview.flendzz.utilis.InternetCheck
import online.interview.flendzz.utilis.Status
import online.interview.flendzz.viewmodel.user.user.UserViewModel

class EmployeeDetail : AppCompatActivity() {

     var activityEmployeeDetailsBind: ActivityEmployeeDetailBinding ?=null;
    lateinit var userViewModel: UserViewModel
     var mEmployeeId:Int?=null;
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityEmployeeDetailsBind=ActivityEmployeeDetailBinding.inflate(layoutInflater)
        setContentView(activityEmployeeDetailsBind!!.root)
        mEmployeeId=intent.getIntExtra("employeeId",0)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        if (InternetCheck.isOnline(applicationContext)){
            getEmployeeDetails();
        }else{
            toast(getString(R.string.no_internet))
        }
    }

    private fun getEmployeeDetails(){
        userViewModel.getEmployeeDetails(mEmployeeId!!)?.observe(this,
            {
                when (it.status) {
                    Status.LOADING -> {
                        toast("Loading")
                    }
                    Status.ERROR -> {
                        toast(it.message.toString())
                    }
                    Status.SUCCESS -> {
                        activityEmployeeDetailsBind!!.user=it.data
                    }
                }
            })
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}