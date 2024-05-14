package live.neddyap.rutbis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    private lateinit var title: TextView

    private fun initView() {
        title = findViewById(R.id.header_title)
//        title.text = resources.getString(R.string.header_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)

//        initView()
    }
}