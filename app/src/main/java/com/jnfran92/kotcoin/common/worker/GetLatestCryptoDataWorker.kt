package com.jnfran92.kotcoin.common.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jnfran92.domain.usecase.crypto.GetCryptoListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class GetLatestCryptoDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val useCase: GetCryptoListUseCase
) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Timber.d("doWork")
        return try {
            val updatedItem = useCase().blockingGet()
            Timber.d("doWork: updated item $updatedItem")
            Result.success()
        }catch (e: Exception){
            Timber.d("doWork: failure $e")
            Result.failure()
        }
    }
}