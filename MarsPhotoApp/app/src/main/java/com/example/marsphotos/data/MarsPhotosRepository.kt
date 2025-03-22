package com.example.marsphotos.data
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

interface MarsPhotosRepository{
    suspend fun getMarsPhotos():List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marApiService.getPhotos()
}