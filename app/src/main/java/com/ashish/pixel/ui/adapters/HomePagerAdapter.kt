package com.ashish.pixel.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ashish.pixel.ui.fragments.collection.CollectionFragment
import com.ashish.pixel.ui.fragments.imagelist.ImageListFragment
import java.lang.IndexOutOfBoundsException

class HomePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentCreator: Map<Int, () -> Fragment> = mapOf(
        IMAGE_LIST_PAGE_INDEX to {ImageListFragment()},
        COLLECTION_PAGE_INDEX to {CollectionFragment()}
    )

    override fun getItemCount(): Int = fragmentCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }



}

const val IMAGE_LIST_PAGE_INDEX = 0
const val COLLECTION_PAGE_INDEX = 1