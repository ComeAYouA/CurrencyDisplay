package com.example.currencydisplay.screen

import android.content.IntentFilter
import android.graphics.drawable.GradientDrawable.Orientation
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.currencydisplay.utils.InternetBroadCastReceiver
import com.example.currencydisplay.R
import com.example.currencydisplay.screen.rv.ExchangeRatesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: AppCompatActivity(){

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var exchangeRatesAdapter: ExchangeRatesAdapter

    private lateinit var infoDateTextView: TextView
    private lateinit var errorMessageTextView: TextView
    private lateinit var exchangeRatesRV: RecyclerView
    private lateinit var loadingIndicator: ProgressBar
    private val internetBroadCastReceiver =  InternetBroadCastReceiver { internetIsActive ->
        if (internetIsActive) viewModel.load()
    }

    override fun onAttachedToWindow() {
        val filter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }

        registerReceiver(internetBroadCastReceiver, filter)

        super.onAttachedToWindow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        infoDateTextView = findViewById(R.id.info_date_text_view)
        errorMessageTextView = findViewById(R.id.error_message)
        exchangeRatesRV = findViewById(R.id.exchange_rate_recycler_view)
        loadingIndicator = findViewById(R.id.loading_indicator)

        setupRv()
        setupObservers()
    }

    private fun setupRv(){
        exchangeRatesRV.apply {
            adapter = exchangeRatesAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    setupScreenStateObserver()
                }

                viewModel.setupSync()
            }
        }
    }

    private suspend fun setupScreenStateObserver(){
        viewModel.screenState.collect{ state ->
            when(state){
                is CurrencyScreenState.Success -> {
                    errorMessageTextView.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.INVISIBLE

                    infoDateTextView.text = "Последнее обновление: " + state.data.date

                    exchangeRatesAdapter.setExchangeRatesList(
                        state.data.exchangeRate.values.toList()
                    )
                }
                is CurrencyScreenState.Loading -> {
                    errorMessageTextView.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.VISIBLE
                }
                is CurrencyScreenState.Error -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    errorMessageTextView.visibility = View.VISIBLE
                    infoDateTextView.text = state.message
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        unregisterReceiver(internetBroadCastReceiver)
        super.onDetachedFromWindow()
    }
}