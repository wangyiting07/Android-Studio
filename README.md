# Android-Studio
This contains different functionalities that are achieved in Android Studio (write in java, not in Kotlin)

# Auto fill website
  This is the script that fill the fields on the website automatically, which is achieved by WebView.

  ### The basic idea
  The basic idea to achieve this is loading that website and using JavaScript directly change the website. So at first, we need to enable the JavaScript. And it's important to enable DomStorage, otherwise this will not work. The basic setting is below:
  ```ruby
  webView.getSettings().setDomStorageEnabled(true);
  webView.getSettings().setJavaScriptEnabled(true);
  ```

  ### Correctly write JavaScript
  In the method we override, we need to use JavaScript to set the field to what we want. Like this:
  ```ruby
  view.loadUrl("javascript: var x = document.getElementById('id of the field that you want to fill').value = '"+content+"';" );
  ```
  Here, we need to know the id of this field to use inside getElementById. If you don't know what's id is that, one way is going to that website. Right click and choose "view page source". Then search for the field you want.

  At last, don't forget set ContentView to show this website.
  ```ruby
  setContentView(webView);
  ```
