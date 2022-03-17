package com.example.lockscreenusingjitpackcompose.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lockscreenusingjitpackcompose.data.models.ListOfBackgroundImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Singleton

@HiltViewModel
@Singleton
class MainViewModel : ViewModel() {

    private val _currentImagePosition = MutableLiveData(0)
    val currentImagePosition: LiveData<Int> = _currentImagePosition

    private var isShuffle : Boolean = true
    private var job : Job? = null

    fun setIsSuffle(isSuffle : Boolean) {
        this.isShuffle = isSuffle
        if(!isSuffle){
            job?.cancel()
            job = null
        }
    }

    fun startImageSuffle(){
        if(job == null) {
            job = viewModelScope.launch(Dispatchers.IO) {
                while (isShuffle) {
                    delay(3000)
                    if (currentImagePosition.value == ListOfBackgroundImages().images.size - 1) {
                        _currentImagePosition.postValue(0)
                    } else {
                        _currentImagePosition.postValue((currentImagePosition.value ?: 0) + 1)
                    }
                }
            }
        }
        job?.start()
    }

}