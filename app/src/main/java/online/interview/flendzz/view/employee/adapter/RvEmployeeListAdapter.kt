package online.interview.flendzz.view.employee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import online.interview.flendzz.databinding.ItemEmployeeAdapterBinding
import online.interview.flendzz.model.UserModelItem

class RvEmployeeListAdapter(var userList: List<UserModelItem>, private val mEmployeeItemListener: EmployeeItemListener) :

        RecyclerView.Adapter<RvEmployeeListAdapter.RvUserListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvUserListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeAdapterBinding.inflate(layoutInflater)
        return RvUserListHolder(binding)
    }

    override fun onBindViewHolder(holder: RvUserListHolder, position: Int) {
        val currentUser = userList[position]
        holder.userListView.user = currentUser
        holder.userListView.executePendingBindings()
        holder.itemView.setOnClickListener {
            mEmployeeItemListener.onViewItem(view = null, position)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(list: List<UserModelItem>) {
        userList = list
    }


    class RvUserListHolder(val userListView: ItemEmployeeAdapterBinding) :
            RecyclerView.ViewHolder(userListView.root)

    interface EmployeeItemListener {
        fun onViewItem(view: View?, position: Int)
    }
}