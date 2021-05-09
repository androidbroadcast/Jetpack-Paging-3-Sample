package dev.androidbroadcast.sample.paging3.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.androidbroadcast.sample.paging3.appComponent
import dev.androidbroadcast.sample.paging3.R
import dev.androidbroadcast.sample.paging3.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Provider

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    @Inject
    lateinit var viewModeProvider: Provider<HomeViewModel.Factory>

    private val viewBinding by viewBinding(ActivityHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels { viewModeProvider.get() }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeNewsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            news.adapter = adapter
            query.doAfterTextChanged { text ->
                viewModel.setQuery(text?.toString() ?: "")
            }
        }
        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.news.collect(adapter::submitList)
        }
        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.query.collect { query ->
                if ((viewBinding.query.text?.toString() ?: "") != query) {
                    viewBinding.query.setText(query)
                }
            }
        }
    }
}