package live.neddyap.rutbis.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selamat Datang di Rutbis!"
    }
    val text: LiveData<String> = _text
}