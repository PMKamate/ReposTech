package com.practicaltest.reposnewapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.practicaltest.reposnewapp.R
import com.practicaltest.reposnewapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

       // val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
       // binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}

    /*fun init(){
        setAdapter()

    }
    fun setAdapter(){
        viewManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        ) as RecyclerView.LayoutManager
        recyclerView.layoutManager = viewManager

        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setNestedScrollingEnabled(false)
    }*/


   /* fun LoadJson() {
        errorLayout.setVisibility(View.GONE)
        swipe_refresh_layout.setRefreshing(true)
        val BASE_URL = "https://newsapi.org/v2/"
        val retrofit = GetRetrofit.instance // this provides new retrofit instance with given url

        val apiInterface: ApiInterface? =
            retrofit?.create(ApiInterface::class.java)
        val country: String = Utils.getCountry().toString()
        val call = apiInterface?.getNews(country, API_KEY)

        call?.enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                if (response.isSuccessful() && response.body()?.getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles = ArrayList<Article>()
                    }
                    articles = response.body()!!.getArticle() as List<Article>
                    adapter = NewsAdapter(articles, activity!!)
                    root.recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    initListener()
                    root.topheadelines.setVisibility(View.VISIBLE)
                    root.swipe_refresh_layout.setRefreshing(false)
                } else {
                    root.topheadelines.setVisibility(View.INVISIBLE)
                    root.swipe_refresh_layout.setRefreshing(false)
                    val errorCode= when (response.code()) {
                        404 -> "404 not found"
                        500 -> "500 server broken"
                        else -> "unknown error"
                    }
                    showErrorMessage(
                        R.drawable.no_result,
                        "No Result",
                        """
                        Please Try Again!
                        $errorCode
                        """.trimIndent()
                    )
                }
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                topheadelines.setVisibility(View.INVISIBLE)
                swipe_refresh_layout.setRefreshing(false)
                showErrorMessage(
                    R.drawable.oops,
                    "Oops..",
                    """
                    Network failure, Please Try Again
                    $t
                    """.trimIndent()
                )
            }
        })
    }


    private fun initListener() {
        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(activity, NewsDetailActivity::class.java)
                val article: Article = articles.get(position)
                intent.putExtra("url", article.getUrl())
                intent.putExtra("title", article.getTitle())
                intent.putExtra("img", article.getUrlToImage())
                intent.putExtra("date", article.getPublishedAt())
                intent.putExtra("source", article.getSource()?.getName())
                intent.putExtra("author", article.getAuthor())
                startActivity(intent)

            }
        })
    }

    override fun onRefresh() {
        LoadJson()
    }



    private fun showErrorMessage(imageView: Int, title: String, message: String) {
        if (root.errorLayout.visibility == View.GONE) {
            root.errorLayout.visibility = View.VISIBLE
        }
        root.errorImage.setImageResource(imageView)
        root.errorTitle.text = title
        root.errorMessage.text = message
        root.btnRetry.setOnClickListener { onLoadingSwipeRefresh() }
    }*/

