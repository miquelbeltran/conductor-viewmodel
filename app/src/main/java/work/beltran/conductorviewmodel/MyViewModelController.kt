package work.beltran.conductorviewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.controller_viewmodel.view.*

class MyViewModelController : ViewModelController() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_viewmodel, container, false)

        val viewModel = viewModelProvider().get(MyViewModel::class.java)

        // LiveData has no observers even after a configuration change
        assert(!viewModel.getLiveData().hasObservers())

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