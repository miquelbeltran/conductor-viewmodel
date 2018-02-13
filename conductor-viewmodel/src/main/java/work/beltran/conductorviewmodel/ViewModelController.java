package work.beltran.conductorviewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

public abstract class ViewModelController extends Controller implements LifecycleOwner {

    private final ViewModelStore viewModelStore = new ViewModelStore();
    private final ControllerLifecycleOwner lifecycleOwner = new  ControllerLifecycleOwner(this);

    protected ViewModelProvider viewModelProvider() {
        return viewModelProvider(new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()));
    }

    protected ViewModelProvider viewModelProvider(ViewModelProvider.NewInstanceFactory factory) {
        return new ViewModelProvider(viewModelStore, factory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModelStore.clear();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleOwner.getLifecycle();
    }
}
