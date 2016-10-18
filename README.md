# Baso Progress View
ProgressBar with button and text below it.

## Sample Usage
### XML Layout
#### Without styles.xml
```
<id.gits.baso.BasoProgressView
    android:id="@+id/baso_ProgressView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_below="@id/baso_btnGroup"
    android:background="#FFF"
    android:gravity="center"
    app:baso_finishButtonText="Retry"
    app:baso_finishSrc="@drawable/baso_sample_error"
    app:baso_finishSrcWidth="160dp"
    app:baso_finishText="Something happened"
    app:baso_finishTextSize="18sp"
    app:baso_progressText="Loading"
/>
```
#### With styles.xml
```
<id.gits.baso.BasoProgressView
    android:id="@+id/baso_ProgressView"
    style="@style/BasoCustom"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_below="@id/baso_btnGroup"
/>
```
```
<style name="BasoCustom">
    <item name="android:background">#FFF</item>
    <item name="baso_finishText">Something happened</item>
    <item name="baso_finishTextSize">18sp</item>
    <item name="baso_finishButtonText">Try Again</item>
    <item name="baso_progressText">Loading</item>
    <item name="baso_finishSrc">@drawable/baso_sample_error</item>
    <item name="baso_finishSrcWidth">160dp</item>
</style>
```

### How to start and stop the progress
#### Start the progress
```
final BasoProgressView basoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);   
basoProgressView.startProgress();
```
#### Stop the progress
```
basoProgressView.stop();

//or you can also set the error text.
basoProgressView.stopAndError("Oops. Something happened.");
```
#### Set the button click listener
```
basoProgressView.setOnButtonClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        basoProgressView.startProgress();
    }
});
```


For complete sample usage, you can take a look at the sample project.

## Install
The latest version is available in the Sonatype snapshots repo.
```
dependencies {
   compile 'id.gits:mvvmcore:0.1'
}
```

## License
```
Copyright 2016 GITS Indonesia.

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
