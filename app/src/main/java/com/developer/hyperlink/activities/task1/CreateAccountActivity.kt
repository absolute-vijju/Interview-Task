package com.developer.hyperlink.activities.task1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.developer.hyperlink.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityCreateAccountBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val terms = "Terms & Conditions"
        val privacy = "Privacy Policy"
        val finalString = "By continuing you agree to Redaa's\n$terms and $privacy"
        SpannableString(finalString).apply {
            setSpan(
                ForegroundColorSpan(Color.parseColor("#A5A6B0")),
                0,
                finalString.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            setSpan(
                ForegroundColorSpan(Color.parseColor("#1E1E1E")),
                35,
                finalString.length - privacy.length - 4,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            setSpan(
                ForegroundColorSpan(Color.parseColor("#1E1E1E")),
                39 + terms.length,
                finalString.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            mBinding.tvTermsAndPrivacy.text = this
        }

        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
}