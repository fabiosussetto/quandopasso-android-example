package com.quandopasso.quandopassoandroidintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.quandopasso.sdk.QPEngine
import com.quandopasso.sdk.QPSettings
import com.quandopasso.sdk.events.QPEvent
import com.quandopasso.sdk.events.QPLocationUpdatedEvent
import com.quandopasso.sdk.events.QPNotifiedVSignEvent
import com.quandopasso.sdk.events.QPVSignsFetchedEvent
import com.quandopasso.sdk.models.VSign


class MainActivity : AppCompatActivity() {

    private lateinit var qpEngine : QPEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings = QPSettings()
        qpEngine = QPEngine(this, settings)
        qpEngine.onEvent = this::onQPEvent
        if (qpEngine.checkPermissions()) {
            Log.i("QuandoPassoIntegration", "qpEngine.start")
            qpEngine.start()
        } else {
            Log.i("QuandoPassoIntegration", "requestPermissions")
            qpEngine.requestPermissions()
        }
    }

    fun onQPEvent(event: QPEvent) {
        Log.i("QuandoPassoIntegration", "Received SDK event ${event}")
    }
}
