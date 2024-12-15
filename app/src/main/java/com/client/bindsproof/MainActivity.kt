package com.client.bindsproof

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.client.bindsproof.di.BindsQualifier
import com.client.bindsproof.di.ProvidesQualifier
import com.client.bindsproof.ui.theme.BindsProofTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ProvidesQualifier
    @Inject
    lateinit var providesRepository: NewsRepository

    @BindsQualifier
    @Inject
    lateinit var bindsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BindsProofTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Hello World")
                    }
                }
            }
        }

        testPerformance()
    }

    private fun testPerformance() {
        val iterations = 1000000
        var temp: NewsRepository? = null

        // Warmup phase to get JIT compilation settled
        repeat(10000) {
            temp = providesRepository
            temp = bindsRepository
        }

        // Multiple measurement rounds
        val rounds = 5
        val providesResults = mutableListOf<Long>()
        val bindsResults = mutableListOf<Long>()

        repeat(rounds) {
            // Test @Provides
            val providesStartTime = System.nanoTime()
            repeat(iterations) {
                temp = providesRepository
            }
            providesResults.add(System.nanoTime() - providesStartTime)

            // Test @Binds
            val bindsStartTime = System.nanoTime()
            repeat(iterations) {
                temp = bindsRepository
            }
            bindsResults.add(System.nanoTime() - bindsStartTime)
        }

        // Calculate averages
        val avgProvides = providesResults.average() / 1000000.0
        val avgBinds = bindsResults.average() / 1000000.0

        Log.d(
            "PerformanceTest", """
        Performance Test Results (Average of $rounds rounds):
        @Provides took: ${avgProvides}ms
        @Binds took: ${avgBinds}ms
        @Binds is ${avgProvides / avgBinds}x faster
    """.trimIndent()
        )
    }
}