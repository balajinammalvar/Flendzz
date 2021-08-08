package online.interview.flendzz.view.employee

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import online.interview.flendzz.R
import online.interview.flendzz.databinding.ActivityEmployeeListBinding
import online.interview.flendzz.model.UserModelItem
import online.interview.flendzz.utilis.InternetCheck
import online.interview.flendzz.utilis.Status
import online.interview.flendzz.view.employee.adapter.RvEmployeeListAdapter
import online.interview.flendzz.viewmodel.user.user.UserViewModel

private const val TAG = "UserDetailsActivity"

class UserDetailsActivity : AppCompatActivity(), RvEmployeeListAdapter.EmployeeItemListener {

    var activityEmployeeBind: ActivityEmployeeListBinding? = null;
    lateinit var userViewModel: UserViewModel
    lateinit var rvUserListAdapter: RvEmployeeListAdapter
    lateinit var mUserModelItem: List<UserModelItem>;

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        activityEmployeeBind = ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(activityEmployeeBind!!.root)

        if (InternetCheck.isOnline(applicationContext)) {
            getEmployeeList();
        } else {
            activityEmployeeBind?.rvUserList?.visibility = View.GONE
            activityEmployeeBind?.noData?.relativeNoData?.visibility = View.VISIBLE
            toast(getString(R.string.no_internet))
        }
        getEmployeeList();
    }

    private fun getEmployeeList() {
        userViewModel.getEmployeeList()?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    toast("Loading")
                }
                Status.ERROR -> {
                    activityEmployeeBind?.rvUserList?.visibility = View.GONE
                    activityEmployeeBind?.noData?.relativeNoData?.visibility = View.VISIBLE
                    toast(it.message.toString())
                }
                Status.SUCCESS -> {
                    activityEmployeeBind?.rvUserList?.visibility = View.VISIBLE
                    activityEmployeeBind?.noData?.relativeNoData?.visibility = View.GONE
                    val data = it.data
                    mUserModelItem = data as List<UserModelItem>
                    setUserList(mUserModelItem)
                }
            }
        })
        loadUserListAdapter();
    }

    private fun setUserList(list: List<UserModelItem>) {
        rvUserListAdapter?.setData(list)
        rvUserListAdapter?.notifyDataSetChanged()
    }

    private fun loadUserListAdapter() {
        rvUserListAdapter = RvEmployeeListAdapter(arrayListOf(), this)
        activityEmployeeBind?.rvUserList?.layoutManager = LinearLayoutManager(applicationContext)
        activityEmployeeBind?.rvUserList?.addItemDecoration(
                DividerItemDecoration(
                        applicationContext,
                        LinearLayoutManager.VERTICAL
                )
        )
        activityEmployeeBind?.rvUserList?.adapter = rvUserListAdapter

    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewItem(view: View?, position: Int) {
        var intent = Intent(applicationContext, EmployeeDetail::class.java)
        intent.putExtra("employeeId", mUserModelItem.get(position).id)
        startActivity(intent)

    }
}