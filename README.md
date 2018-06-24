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

# Remember User Login Status (No Backend)
This part contains "LoginActivity","SignUpActivity" and "LaunchActivity".
  Use getSharedPreferences to remeber user login status. This method work good for ones don't have backend. And just want user to sign up withour using google acount or others. If you have server or other backend, maybe AccountManager or other tools are more suitable for you.

  I guess most people have backend nowadays. I just record this method here.

  ### Basic idea
  The basic idea is setting getSharedPreferences in sugn up activity, put the password, username that user input into getSharedPreferences. And this can be accessed in every activity and keep consistant.
  ```ruby
  getSharedPreferences(UserInfo,MODE_PRIVATE)
          .edit()
          .putString(PREF_PASSWORD,Password)
          .putString(PREF_USERNAME,UserName).commit();
  ```
  Remind: don't forget commit. I forgot that and debug for that using a lot time.

  ### Remember user login Status
  In login activity, get the username and password that user set before and comparing that with what user input in login page.
  ```ruby
  userInfo = getSharedPreferences(UserInfo,MODE_PRIVATE);
  knownUser = userInfo.getString("UserName", null);
  knownPassword = userInfo.getString("Password", null);
  String name = userName.getText().toString();
  String word = password.getText().toString();
  if ((name.equals(knownUser)) && (word.equals(knownPassword))) {
      userInfo.edit().putBoolean("logged", true).commit();
      canLogin = true;
  }
  ```
  The way that I remember the user login is putting a boolean variable into getSharedPreferences named logged. If their login info is correct, set this variable to true. And in the launch activity, see this variable. If it's true, then user has already logged in, skip the login page, directly jump to other pages. If logged variable is false, then go to log in activity, let user to log in or sign up.

    ### Make Launch Activity Last for longer time.
    At first, the launch page lasted too short to see it. So I use handler to let it last longer.
    ```ruby
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(activityIntent);
            finish();
        }
    },2000);  //2s
    ```
