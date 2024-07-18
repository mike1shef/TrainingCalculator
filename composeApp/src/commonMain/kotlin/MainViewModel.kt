import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.RepositoryImpl
import database.model.BodyMeasurements
import database.model.Event
import database.model.Weight
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private val _trainings = repository.trainings
    val trainings get() = _trainings

    private var _valueToPay = MutableStateFlow(0.0)
    val valueToPay get() = _valueToPay

    private val _weights = repository.weights.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private var _lastWeight  = mutableStateOf<Weight>(Weight())
    val lastWeight get() = _lastWeight

    private val _lastMeasurements  = mutableStateOf(BodyMeasurements())

    val lastMeasurements get() = _lastMeasurements

    fun addTraining(event: Event) {
        viewModelScope.launch {
            repository.addTraining(event)
        }
    }

    fun getTheLastWeight() {
        viewModelScope.launch{
        _weights.collect { weights ->
            if (weights.isNotEmpty()) {
                val sortedWeights = weights.sortedByDescending { weight: Weight -> weight.id  }
                val lastWeight = sortedWeights.first()
                this@MainViewModel._lastWeight.value = lastWeight
            }
        } }
    }


    fun calcTrainingsToPay(){
        viewModelScope.launch {
            _trainings.collect { trainings ->
                val unpaidTrainings = trainings.filter { !it.isPaid }
                _valueToPay.value = unpaidTrainings.sumOf { it.cost }
            }
        }
    }

    fun getTheLastBodyMeasurements(){
        viewModelScope.launch {
            repository.getTheLastBodyMeasurements().collect { bodyMeasurements ->
                if (bodyMeasurements != null) {
                    _lastMeasurements.value = bodyMeasurements
                }
            }
        }
    }

    fun bodyMeasurementIsEmpty () : Boolean {
        return if (_lastMeasurements.value.id == 0) true else false
    }

    fun bodyWeightIsEmpty () : Boolean {
        return if (_lastWeight.value.id == 0) true else false
    }

    fun addBodyMeasurement(bodyMeasurement: BodyMeasurements) {
        viewModelScope.launch {
            repository.addMeasurement(bodyMeasurement)
        }
    }

    fun addWeight (weight: Weight) {
        viewModelScope.launch {
            repository.addWeight(weight)
        }
    }
}