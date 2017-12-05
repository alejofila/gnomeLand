package com.example.alejofila.gnomeland

import com.example.alejofila.gnomeland.common.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Scheduler provider implementation for testing purposes
 */
class TestSchedulerProvider(val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler {
        return testScheduler
    }

    override fun computation(): Scheduler {
        return testScheduler
    }

    override fun io(): Scheduler {
        return testScheduler
    }
}