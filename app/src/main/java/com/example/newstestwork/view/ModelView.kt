package com.example.newstestwork.view

import androidx.lifecycle.*
import com.example.newstestwork.service.ApiService
import com.example.newstestwork.service.Action
import com.example.newstestwork.service.JsonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class ModelView : ViewModel() {
    var api: ApiService = JsonService.retrofitService()

    fun <News> requestData(
        liveData: MutableLiveData<Action<List<News>>>,
        request: suspend () -> List<News>
    ) {
        liveData.postValue(Action.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()

                if (!response.isNullOrEmpty()) {
                    liveData.postValue(Action.success(response))

                } else {
                    liveData.postValue(Action.error(null))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Action.error(null))
            }
        }
    }

}