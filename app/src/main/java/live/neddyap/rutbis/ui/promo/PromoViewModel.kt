package live.neddyap.rutbis.ui.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PromoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Nikmati Promo Menarik dari Kami!"
    }
    val text: LiveData<String> = _text
}