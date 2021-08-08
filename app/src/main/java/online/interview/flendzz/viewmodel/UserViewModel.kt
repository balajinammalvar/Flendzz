package online.interview.flendzz.viewmodel.user.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.emperor.kotlinexample.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import online.interview.flendzz.repository.UserRepo
import kotlin.coroutines.CoroutineContext

private const val TAG = "UserViewModel"

class UserViewModel : ViewModel(), CoroutineScope {
    private val job = Job()// Should cancel this on Destroy

    fun getEmployeeList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = UserRepo.getEmployee()
            emit(Resource.success(data = data))
        } catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getEmployeeDetails(mEmployeeId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = UserRepo.getEmployeeDetails(mEmployeeId)
            emit(Resource.success(data = data))
        } catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}