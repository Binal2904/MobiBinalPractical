package com.task.mobiiworldbinalpractcal.view.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.task.mobiiworldbinalpractcal.databinding.LayoutToolbarBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = setBinding(inflater, container)
        return binding.root
    }

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): VB


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mInitView(view)
    }


    protected abstract fun mInitView(view: View)

    protected open fun openActivity(destinationClass: Class<*>?) {
        startActivity(Intent(requireActivity(), destinationClass))
    }



    protected open fun isEditTextEmpty(editText: EditText?): Boolean {
        return editText == null || editText.text.toString().isEmpty()
    }

    protected open fun manageError(mMessage: String, view: View): Boolean {
        messageSnackBar(mMessage, view)
        return false
    }

    protected fun messageSnackBar(strTitle: String, view: View) {
        Snackbar.make(requireContext(), view, strTitle, Snackbar.LENGTH_SHORT).show()
    }

}