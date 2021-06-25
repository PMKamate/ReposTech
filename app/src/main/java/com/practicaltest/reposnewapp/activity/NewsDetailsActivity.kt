package com.practicaltest.reposnewapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.devs.readmoreoption.ReadMoreOption
import com.google.android.material.appbar.AppBarLayout
import com.practicaltest.reposnewapp.R
import com.practicaltest.reposnewapp.data.entities.NewsDetail
import com.practicaltest.reposnewapp.utils.Utils
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private var mUrl: String? = null
    private var mImg: String? = null
    private var mTitle: String? = null
    private var mdesc: String? = null
    private var mSourceName: String? = null
    private var isHideToolbarView = false

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.setTitle("")
        appbar.addOnOffsetChangedListener(this)

        val intent: Intent = getIntent()
        mUrl = intent.getStringExtra("url")
        mImg = intent.getStringExtra("img")
        mTitle = intent.getStringExtra("title")
        mdesc = intent.getStringExtra("desc")
        mSourceName = intent.getStringExtra("sourceName")

        val requestOptions = RequestOptions()
        requestOptions.error(Utils.getRandomDrawbleColor())
        Glide.with(this)
            .load(mImg)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(backdrop)
        title_on_appbar!!.text = mSourceName
        author!!.text = mSourceName
       // subtitle_on_appbar!!.text = mUrl
        tvtitle.setText(mTitle)
       // desc.setText(mdesc)

        desc.setText(Html.fromHtml("$mdesc<font color='#50FFFFFF'> <u>Read More</u></font>"));

        desc.setOnClickListener {
            val intent = Intent(applicationContext, NewsDetailsActivityWeb::class.java)
            intent.putExtra("url", mUrl)
            intent.putExtra("title", mTitle)
            intent.putExtra("sourceName", mSourceName)

            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

   override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScroll: Int = appBarLayout.getTotalScrollRange()
        val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()
        if (percentage == 1f && isHideToolbarView) {
            title_appbar!!.visibility = View.VISIBLE
            author!!.visibility = View.GONE
            isHideToolbarView = !isHideToolbarView
        } else if (percentage < 1f && !isHideToolbarView) {
            title_appbar!!.visibility = View.GONE
            author!!.visibility = View.VISIBLE
            isHideToolbarView = !isHideToolbarView
        }
    }


}
