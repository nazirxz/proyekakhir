package com.nazirman.proyekakhir.ui.lens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LensViewModel() : ViewModel()  {
//    private val _showLoading = MutableLiveData<Boolean>()
//    val showLoading: LiveData<Boolean> = _showLoading
//
//    private val _returnResponse = MutableLiveData<ReturnResponse>()
//    val returnResponse: LiveData<ReturnResponse> = _returnResponse
//
//    private val _dataPredict = MutableLiveData<PredictPetRes>()
//    val dataPredict: LiveData<PredictPetRes> = _dataPredict
//
//    fun getPredictPet(image: MultipartBody.Part) {
//        _showLoading.value = true
//        val service = ApiConfig.getApiServiceML().getPredictPet(file = image)
//        service.enqueue(object : Callback<PredictPetRes> {
//            override fun onResponse(call: Call<PredictPetRes>, response: Response<PredictPetRes>) {
//                val responses = response
//                if(response.code() == 400){
//                    _returnResponse.postValue(ReturnResponse(status = response.code(), message = response.message()))
//                }else{
//                    val responseBody = response.body()
//                    if(responseBody?.error == null){
//                        _dataPredict.postValue(responseBody)
//                        _returnResponse.postValue(ReturnResponse(status = response.hashCode(), message = response.message()))
//                    }else{
//                        _returnResponse.postValue(ReturnResponse(status = response.hashCode(), message = response.body()?.error.toString()))
//                    }
//                }
//
//                _showLoading.value = false
//            }
//
//            override fun onFailure(call: Call<PredictPetRes>, t: Throwable) {
//                _returnResponse.postValue(ReturnResponse(status = 500, message = t.message.toString()))
//                _showLoading.value = false
//            }
//        })
//    }
}