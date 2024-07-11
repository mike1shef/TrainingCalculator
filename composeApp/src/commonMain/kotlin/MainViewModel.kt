import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.RepositoryImpl
import database.model.Event
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private val _trainings = repository.trainings
    val trainings get() = _trainings

    val payments = repository.payments
    val measurements = repository.measurements

    fun addTraining(event: Event) {
        viewModelScope.launch {
            repository.addTraining(event)
        }
    }
}