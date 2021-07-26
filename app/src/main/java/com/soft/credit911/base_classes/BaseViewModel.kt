package com.soft.credit911.base_classes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){
    var apiError = MutableLiveData<String>()
    var onFailure = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var innerLoder = MutableLiveData<Boolean>()
    var loadMore = MutableLiveData<Boolean>()
}