# Baso Progress View
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/id.gits/baso/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/id.gits/baso/) 

ProgressBar with button and text below it.
<p>
<img src="https://raw.githubusercontent.com/gitsindonesia/baso/master/image1.png" alt="Progressing" width="250"/>
<img src="https://raw.githubusercontent.com/gitsindonesia/baso/master/image2.png" alt="Stopped without image" width="250"/>
<img src="https://raw.githubusercontent.com/gitsindonesia/baso/master/image3.png" alt="Stopped with image" width="250"/>
</p>
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
#### Stop progress and show the button
If you have declared ```baso_finishButtonText``` on your xml layout, you can just call ```stop``` method.
```
basoProgressView.stop();
```
Or you have to call ```stopAndError```.
```
basoProgressView.stopAndError("Oops. Something happened.");
```
#### Stop progress and show both image or button
You have to declare ```baso_finishSrc``` either on your xml layout or programmatically with ```setFinishedImageResource``` method.
```
basoProgressView.setFinishedImageResource(R.drawable.baso_sample_error);
basoProgressView.stopAndError("Oops. Something happened.");
```
#### Set the button click listener
Don't forget to create click listener for the button.
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
   compile 'id.gits:baso:1.0'
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
