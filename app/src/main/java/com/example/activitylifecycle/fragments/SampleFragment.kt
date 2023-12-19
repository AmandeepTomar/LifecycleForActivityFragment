package com.example.activitylifecycle.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.activitylifecycle.MainActivity
import com.example.activitylifecycle.MyActivityObserver
import com.example.activitylifecycle.R
import com.example.activitylifecycle.databinding.FragmentSampleBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SampleFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSampleBinding? = null
    private val binidng get() = _binding
    private val TAG = "SampleFragment"


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach: ", )
        //this is how we can use alternative of onActivityCreated().
        activity?.lifecycle?.addObserver(MyActivityObserver{
            Log.e(TAG, "onAttach: ${context as MainActivity }", )
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e(TAG, "onCreate: ")
        //retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSampleBinding.inflate(layoutInflater, container, false)
        Log.e(TAG, "onCreateView: ")

        return binidng?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated: ")
        binidng?.apply {
            val fragment = SampleFragment2.newInstance()
            btnUsingAdd.setOnClickListener {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
//                    addToBackStack("SampleFragment2")
                    add(R.id.FragmentContainerView,fragment)
                }
            }

            btnUsingReplace.setOnClickListener {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    addToBackStack("SampleFragment2")
                    replace(R.id.FragmentContainerView,fragment)
                }
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(TAG, "onViewStateRestored: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }


    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState: ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach: ")
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SampleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}