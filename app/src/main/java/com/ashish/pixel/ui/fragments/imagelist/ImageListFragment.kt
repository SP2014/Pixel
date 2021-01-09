package com.ashish.pixel.ui.fragments.imagelist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ashish.pixel.R
import com.ashish.pixel.data.models.PixelImages
import com.ashish.pixel.data.models.Result
import com.ashish.pixel.databinding.FragmentImageListBinding
import com.ashish.pixel.ui.adapters.PixelImageAdapter
import com.ashish.pixel.ui.fragments.home.HomeFragmentDirections

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentImageListBinding
    private val viewModel: ImageListViewModel by activityViewModels()

    private val imageAdapter = PixelImageAdapter(this::onItemClicked)

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
        binding = FragmentImageListBinding.inflate(inflater, container, false)

        setImagesObserver()
        setImagesRecyclerView()
        setSwipeRefreshLayout()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setImagesObserver() {
        viewModel.images.observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                }
                is Result.Success -> {
                    showImagesRecyclerView(result.data!!)
                }
                is Result.Empty ->{
                    val message = getString(R.string.no_image_show)
                    showEmptyView(message)
                }
                is Result.Error -> {
                    val message = if (result.isNetworkError) {
                        getString(R.string.no_internet)
                    } else {
                        getString(R.string.no_image_show)
                    }
                    showEmptyView(message)
                }
            }
        }
    }

    private fun showEmptyView(message: String) {
        with(binding) {
            // Stop refreshing state
            swipeRefreshLayout.isRefreshing = false

            imagesRecyclerView.visibility = View.INVISIBLE
            noDataGroup.visibility = View.VISIBLE

            noDataText.text = message
        }

        // Submit an empty list since there is no data
        imageAdapter.submitList(emptyList())
    }

    private fun showImagesRecyclerView(images: PixelImages) {
        with(binding) {
            // Stop refreshing state
            swipeRefreshLayout.isRefreshing = false

            imagesRecyclerView.visibility = View.VISIBLE
            noDataGroup.visibility = View.GONE
        }

        // Submit the list of images
        imageAdapter.submitList(images)
    }

    private fun setImagesRecyclerView() {
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageAdapter
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.isEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_images, menu)
        setSearchView(menu)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun onItemClicked(position: Int){
        val images = imageAdapter.currentList.toTypedArray()
        val directions = HomeFragmentDirections.actionHomeFragmentToGalleryFragment(position, images)
        findNavController().navigate(directions)
    }

    /**
     * SearchView configuration.
     */
    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)

        (searchItem.actionView as SearchView).apply {
            // Set hint text
            queryHint = getString(R.string.pixel)

            // Set listener for user actions
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.setQuery(newText)
                    return true
                }
            })
        }
    }
}