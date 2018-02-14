# ViewModel support for Conductor

This library adds support for the ViewModel from Architecture Components
to the Conductor library.

[![Release](https://jitpack.io/v/miquelbeltran/conductor-viewmodel.svg)](https://jitpack.io/#miquelbeltran/conductor-viewmodel)

## How it works

A ViewModelController is provided that contains all the necessary things to
create ViewModels and observe LiveData.

The ViewModelController is a LifecycleOwner that also provides the necessary
events for observing LiveData.

The ViewModelController contains its own ViewModelStore, which contains the
ViewModels. As Controllers survive configuration changes, the ViewModels will
do as well.

## How to use it

```kotlin
// 1. Create a new Controller by extending the ViewModelController.
class MyViewModelController : ViewModelController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_viewmodel, container, false)

        // 2. Obtain your ViewModel using the viewModelProvider() method
        val viewModel = viewModelProvider().get(MyViewModel::class.java)

        // 3. Observe your LiveData
        viewModel.getLiveData().observe(this, Observer<String> {
            //
        })

        return view
    }
}

```

## Installing

This library depends on Architecture Components and Conductor.

Those two components **are NOT provided** and need to be added to your project
separately.

Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    compile 'com.github.miquelbeltran:conductor-viewmodel:1.0.1'
}
```

## FAQ

- When is the LiveData observer detached?

The LiveData observer will be removed (detached) after onDestroyView is called.
You don't need to remove the observer manually

- When should I start observing my LiveData?

Call to liveData.observe() on the onCreateView method.

- How can I provide a ViewModel factory?

Call `viewModelProvider` and pass your factory as the first parameter.

- Are there any samples?

A sample project is included in the `app` module.

- Why there are no tests?

I will add Espresso tests in the future to verify that lifecycle events happen correctly.

- Why is the ON_DESTROY event in onDestroyView?

In order to remove the observers from the LiveData when the view is destroyed.

## License

Conductor is a library by BlueLine Labs, Inc.

Conductor ViewModel is a library by Miquel Beltran

```
Copyright 2018 Miquel Beltran - BELTRAN.WORK

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


