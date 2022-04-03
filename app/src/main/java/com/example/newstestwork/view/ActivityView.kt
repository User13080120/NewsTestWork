package com.example.newstestwork.view

import androidx.lifecycle.MutableLiveData
import com.example.newstestwork.model.DataJson
import com.example.newstestwork.service.Action

class ActivityView : ModelView() {
    val simpleLiveData = MutableLiveData<Action<List<DataJson>>>()

    fun getData() {
        requestData(simpleLiveData) {
            api.getData()
        }
    }
}