package com.katheryn.todoapp.Util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TodoWorker(val context: Context, val params: WorkerParameters)
    :Worker(context, params) {
    override fun doWork(): Result {
        notificationHelper(context)
            .createNotification(inputData.getString("title").toString(),
                                inputData.getString("message").toString())

        return Result.success()
    }
}