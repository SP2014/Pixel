package com.ashish.pixel.ui.fragments.imagelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ashish.pixel.data.models.PixelImages
import com.ashish.pixel.data.models.Result
import com.ashish.pixel.data.repository.PixelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
class ImageListViewModel @ViewModelInject constructor(pixelRepository: PixelRepository): ViewModel() {
    private val query = MutableStateFlow<String>("")

    val images: LiveData<Result<PixelImages>> = query
            .debounce(700L)
            .distinctUntilChanged()
            .flatMapLatest { text ->
                if(text.isEmpty()) pixelRepository.getCuratedImages()
                else emptyFlow()
            }.asLiveData()


            //pixelRepository.getCuratedImages().asLiveData()

    fun setQuery(text: String) {
        query.value = text
    }

}