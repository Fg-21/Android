package com.example.ejemplomvvm.viewmodels
import androidx.compose.runtime.State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ejemplomvvm.models.RepoUsers
import com.example.ejemplomvvm.models.User

class UserVM : ViewModel() {
    private val _repo = RepoUsers
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: State<List<User>> = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = _repo.getUsers()
    }
    fun insertUser(user:User){
        _repo.insert(user)
        loadUsers()
    }
}
