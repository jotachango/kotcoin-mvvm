package com.jnfran92.kotcoin.common.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jnfran92.domain.tissue.crypto.GetCryptoListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class GetLatestCryptoDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCryptoListUseCase: GetCryptoListUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Timber.d("doWork")
        return try {
            val updatedItem = getCryptoListUseCase()
            Timber.d("doWork: updated item $updatedItem")
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "doWork: failure")
            Result.failure()
        }
    }
}