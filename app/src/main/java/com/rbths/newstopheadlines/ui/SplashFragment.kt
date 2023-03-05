package com.rbths.newstopheadlines.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.rbths.newstopheadlines.databinding.FragmentSplashBinding
import com.rbths.newstopheadlines.utils.Constants.Companion.SPLASH_SCREEN_MILLIS
import com.rbths.newstopheadlines.viewmodel.MainViewModel
import java.util.concurrent.Executor


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // we will show our splash screen before the app opens
    private val handlerSplash = Handler(Looper.getMainLooper())

    private lateinit var mViewModel: MainViewModel

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        val view = binding.root



        executor = ContextCompat.getMainExecutor(requireContext())

        //biometric authentication result callback
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    //call headlines
                    mViewModel.getHeadlines()

                    handlerSplash.postDelayed({
                        val action = SplashFragmentDirections.actionSplashFragmentToArticleListFragment()
                        findNavController().navigate(action)
                    },
                        SPLASH_SCREEN_MILLIS)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    //call headlines
                    mViewModel.getHeadlines()

                    val action = SplashFragmentDirections.actionSplashFragmentToArticleListFragment()
                    findNavController().navigate(action)

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                }
            })


        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()



        biometricPrompt.authenticate(promptInfo)


        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        handlerSplash.removeCallbacksAndMessages(null)
        _binding=null
    }

}