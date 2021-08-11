package kr.co.yeaeun.viewbasic

import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lab11_2.*


class webView_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab11_2)

        lineBtn.setOnClickListener { webView.loadUrl("javascript:lineChart()") }
        barBtn.setOnClickListener { webView.loadUrl("javascript:barChart()") }

        val settings = webView.settings
        settings.javaScriptEnabled = true

        // aseets/ url은 baseUrl이 file:///android_asset/ 이다.
        webView.loadUrl("file:///android_asset/test.html")
        webView.addJavascriptInterface(JavascriptTest(), "android")
        webView.webViewClient = MyWebClient()
        webView.webChromeClient = MyWebChrome()
    }

    internal class JavascriptTest {
        @get:JavascriptInterface
        val chartData: String
            get() {
                val buffer = StringBuffer() // 문자열 자체만
                buffer.append("[")
                for (i in 0..13) {
                    buffer.append("[" + i + "," + Math.sin(i.toDouble()) + "]")
                    if (i < 13) buffer.append(",")
                }
                buffer.append("]")
                return buffer.toString()
            }
    }
    // 사용자 이벤트
    inner class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            val toast = Toast.makeText(this@webView_MainActivity, url, Toast.LENGTH_SHORT)
            toast.show()
            return true
        }
    }
    // 브라우저 자체 이벤트
    inner class MyWebChrome : WebChromeClient() {
        override fun onJsAlert(
            view: WebView,
            url: String,
            message: String,
            result: JsResult
        ): Boolean {
            val toast = Toast.makeText(this@webView_MainActivity, message, Toast.LENGTH_SHORT)
            toast.show()
            result.confirm()
            return true
        }
    }
}