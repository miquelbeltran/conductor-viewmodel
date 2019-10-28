package work.beltran.conductorviewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.controller_viewmodel.view.*

class MyViewModelController : ViewModelController() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_viewmodel, container, false)

        val viewModel = viewModelProvider(MyViewModelFactory()).get(MyViewModel::class.java)

        // LiveData has no observers even after a configuration change
        Log.d(TAG, "hasObservers should be false: ${viewModel.getLiveData().hasObservers()}")

        viewModel.getLiveData().observe(this, Observer<String> {
            this.view?.run {
                text.text = "ViewModel created at: $it"
            }
        })

        return view
    }

    companion object {
        private const val TAG = "MyViewModelController"
    }
}