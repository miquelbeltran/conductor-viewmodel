package work.beltran.conductorviewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

public abstract class ViewModelController extends Controller implements LifecycleOwner {

    private final ViewModelStore viewModelStore = new ViewModelStore();
    private final ControllerLifecycleOwner lifecycleOwner = new ControllerLifecycleOwner(this);

    public ViewModelController() {
        super();
    }

    public ViewModelController(Bundle bundle) {
        super(bundle);
    }

    public ViewModelProvider viewModelProvider() {
        return viewModelProvider(new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()));
    }

    public ViewModelProvider viewModelProvider(ViewModelProvider.Factory factory) {
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
