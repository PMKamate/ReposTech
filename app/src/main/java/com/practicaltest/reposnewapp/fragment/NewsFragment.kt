package com.practicaltest.reposnewapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicaltest.reposnewapp.R
import com.practicaltest.reposnewapp.activity.NewsDetailActivity
import com.practicaltest.reposnewapp.adapter.NewsAdapter
import com.practicaltest.reposnewapp.data.entities.NewsDetail
import com.practicaltest.reposnewapp.databinding.NewsFragmentBinding
import com.practicaltest.reposnewapp.model.news.Article
import com.practicaltest.reposnewapp.utils.Resource
import com.practicaltest.reposnewapp.utils.Utils
import com.practicaltest.reposnewapp.utils.autoCleared
import com.practicaltest.reposnewapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import om.practicaltest.reposnewapp.utils.CommonUtils
import java.util.ArrayList

@AndroidEntryPoint
class NewsFragment : Fragment() {

    val API_KEY = "a6c1c11ccd4a4d22ab54cbdb9edf371e"
    private var binding: NewsFragmentBinding by autoCleared()
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mNewsDetails = ArrayList<NewsDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setAdapter()
        setRVLayoutManager()
        initListener()
    }

    private fun setAdapter() {
        adapter = context?.let { NewsAdapter(mNewsDetails, it) }!!
        adapter.notifyDataSetChanged()
        binding.rvRestaurantlist.adapter = adapter
    }
    private fun initListener() {
        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(activity, NewsDetailActivity::class.java)
                val newsDetail: NewsDetail = mNewsDetails.get(position)
                intent.putExtra("url", newsDetail.url)
                intent.putExtra("title", newsDetail.title)
                intent.putExtra("img", newsDetail.urlToImage)
                intent.putExtra("desc", newsDetail.description)
                intent.putExtra("sourceName", newsDetail.sourceName)
                startActivity(intent)

            }
        })
    }
    private fun setRVLayoutManager() {
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvRestaurantlist.layoutManager = linearLayoutManager
        binding.rvRestaurantlist.setHasFixedSize(true)
    }
    private fun setupObservers() {
        val country: String? = context?.let { Utils.getCountryCode(it).toString() }
        getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        viewModel.getNewsDetails(country.toString(),API_KEY).observe(
            viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING ->
                        binding.progressBar.visibility = View.VISIBLE
                }
            })

    }

}