package com.ashish.pixel.ui.fragments.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ashish.pixel.R
import com.ashish.pixel.data.models.PixelImage
import com.ashish.pixel.databinding.FragmentGalleryBinding
import com.ashish.pixel.ui.adapters.GalleryPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentGalleryBinding
    private val args: GalleryFragmentArgs by navArgs()

    private val galleryAdapter = GalleryPagerAdapter(this::onItemClicked)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        setViewPager()
        setFullscreen()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        exitFullscreen()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GalleryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                GalleryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    /**
     * ViewPager configuration.
     */
    private fun setViewPager() {
        // Set ViewPager adapter
        binding.viewPager.adapter = galleryAdapter

        // Submit list of received images
        val images = args.images.toList()
        galleryAdapter.submitList(images)

        // Move to the selected image
        val position = args.position
        binding.viewPager.setCurrentItem(position, false)
    }

    private fun onItemClicked(image: PixelImage) {
        // Get direction and pass the image
        //val direction = GalleryFragmentDirections.galleryFragmentToImageInfoDialogFragment(image)

        // Navigate to ImageInfoDialogFragment
        //findNavController().navigate(direction)
    }

    /**
     * Enable fullscreen mode.
     */
    private fun setFullscreen() {
        activity?.window?.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /**
     * Disable fullscreen mode.
     */
    private fun exitFullscreen() {
        activity?.window?.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}