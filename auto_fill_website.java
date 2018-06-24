final String content = "content that you want to fill in";
WebView webView = new WebView(this);
webView.getSettings().setDomStorageEnabled(true);
webView.getSettings().setJavaScriptEnabled(true);
webView.loadUrl("https://www.google.com/");
webView.setWebViewClient(new WebViewClient(){
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript: var x = document.getElementById('id of the field that you want to fill').value = '"+content+"';" );
    }
});
setContentView(webView);
