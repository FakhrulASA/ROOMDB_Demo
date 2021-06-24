package com.example.notetodo_roomdb


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NewWordActivity : AppCompatActivity() {
    private lateinit var mEditWordView: EditText
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        mEditWordView = findViewById(R.id.edit_text_word)
        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener { view ->
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}