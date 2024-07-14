import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.RepositoryImpl
import database.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private val _trainings = repository.trainings
    val trainings get() = _trainings

    private var _valueToPay = MutableStateFlow(0.0)
    val valueToPay get() = _valueToPay
    val payments = repository.payments
    val measurements = repository.measurements

    fun addTraining(event: Event) {
        viewModelScope.launch {
            repository.addTraining(event)
        }
    }

    fun calcTrainingsToPay(){
        viewModelScope.launch {
            _trainings.collect { trainings ->
                val unpaidTrainings = trainings.filter { !it.isPaid }
                _valueToPay.value = unpaidTrainings.sumOf { it.cost }
            }
        }
    }
}