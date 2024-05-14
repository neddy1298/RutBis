package live.neddyap.rutbis.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Jelajahi Rutbis!"
    }
    val text: LiveData<String> = _text
}