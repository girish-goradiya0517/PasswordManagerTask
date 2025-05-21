package com.girish.task.data.remote

import com.girish.task.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GoogleScraper {

    suspend fun getSimilarNamedWebsiteUrl(searchName: String): Flow<ApiResponse<String>>

}