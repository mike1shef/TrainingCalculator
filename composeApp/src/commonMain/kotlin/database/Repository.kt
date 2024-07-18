package database

import database.model.BodyMeasurements
import database.model.Event
import database.model.Payment
import database.model.Weight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Repository {
    suspend fun addTraining(event: Event)
    suspend fun addMeasurement(measurement : BodyMeasurements)
    suspend fun addPayment(payment: Payment)
    suspend fun addWeight(weight: Weight)
    suspend fun deleteTraining(event: Event)
    suspend fun deleteMeasurement(measurement : BodyMeasurements)
    suspend fun deletePayment(payment: Payment)
    fun getTheLastBodyMeasurements() : Flow<BodyMeasurements?>
}

class RepositoryImpl (private val dao: TrainingsDAO) : Repository {

    val trainings : Flow<List<Event>> = dao.getAllTrainings()
    val weights : Flow <List<Weight>> = dao.getAllWeights()

    override suspend fun addTraining(event: Event){
        dao.addTraining(event)
    }
    override suspend fun addMeasurement(measurement : BodyMeasurements){
        dao.addBodyMeasurements(measurement)
    }

    override suspend fun addWeight(weight: Weight){
        dao.addWeight(weight)
    }

    override suspend fun addPayment(payment: Payment){
        dao.addPayment(payment)
    }

    override suspend fun deleteTraining(event: Event) {
        dao.deleteTraining(event)
    }

    override suspend fun deleteMeasurement(measurement : BodyMeasurements){
        dao.deleteBodyMeasurements(measurement)
    }

    override suspend fun deletePayment(payment: Payment){
        dao.deletePayment(payment)
    }

    override fun getTheLastBodyMeasurements() : Flow<BodyMeasurements?> {
        return dao.getTheLastBodyMeasurements().map { bodyMeasurements ->
            bodyMeasurements ?: BodyMeasurements()
        }
    }
}