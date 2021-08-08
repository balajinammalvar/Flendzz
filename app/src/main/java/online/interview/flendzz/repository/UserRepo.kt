package online.interview.flendzz.repository

import com.emperor.kotlinexample.api.Retroclient
import online.interview.flendzz.model.UserModelItem

class UserRepo {

    companion object {
        suspend fun getEmployee(): List<UserModelItem>? {
            val response = Retroclient.apiService.getEmployee()

            return response
        }

        suspend fun getEmployeeDetails(mEmployeeId: Int): UserModelItem? {
            val response = Retroclient.apiService.getEmployeeDetails(mEmployeeId)

            return response
        }
    }
}