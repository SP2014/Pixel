package com.ashish.pixel.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ashish.pixel.R
import com.ashish.pixel.databinding.FragmentHomeBinding
import com.ashish.pixel.ui.adapters.COLLECTION_PAGE_INDEX
import com.ashish.pixel.ui.adapters.HomePagerAdapter
import com.ashish.pixel.ui.adapters.IMAGE_LIST_PAGE_INDEX
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.viewPager.adapter = HomePagerAdapter(this)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        // Set TabLayout icons and texts and attach it to ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            IMAGE_LIST_PAGE_INDEX -> R.drawable.images_tab_selector
            COLLECTION_PAGE_INDEX -> R.drawable.collection_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * Get TabLayout titles.
     */
    private fun getTabTitle(position: Int): String {
        return when (position) {
            IMAGE_LIST_PAGE_INDEX -> getString(R.string.images)
            COLLECTION_PAGE_INDEX -> getString(R.string.my_collection)
            else -> throw IndexOutOfBoundsException()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}