package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : Activity() {

    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    private var conteudoRSS: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        doAsync {
            try {
                getRssFeed(RSS_FEED)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    //Opcional - pesquise outros meios de obter arquivos da internet - bibliotecas, etc.
    private fun getRssFeed(feed: String):String {
        var input: InputStream? = null
        var rssFeed: String
        try {
            val url: URL = URL(feed)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            input = conn.inputStream
            var out = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var count = input.read(buffer)
            while (count != -1 ) {
                out.write(buffer, 0, count)
                count = input.read(buffer)
            }
            var response: ByteArray = out.toByteArray()
            rssFeed = String(response, charset("UTF-8"))
            val parsedRss = ParserRSS.parse(rssFeed)
            println(parsedRss)
            conteudoRSS?.adapter = RSSadapter(parsedRss, applicationContext)
        } finally {
            if (input != null) {
                input.close()
            }
        }
        return rssFeed
    }
}
