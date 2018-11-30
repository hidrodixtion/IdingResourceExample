package com.projectbox.idlingresourceexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"

        EspressoIdlingResource.increment()
        GlobalScope.launch(Dispatchers.Main) {
            val response = Gson().fromJson(buildRequest(url).await(), TeamResponse::class.java)

            response.teams.firstOrNull()?.let {
                txt_team.text = it.strTeam
            }

            EspressoIdlingResource.decrement()
        }
    }

    private fun buildRequest(url: String): Deferred<String> = GlobalScope.async { URL(url).readText() }
}
