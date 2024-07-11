package database

import database.model.BodyMeasurements
import database.model.Event
import database.model.Payment
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun addTraining(event: Event)
    suspend fun addMeasurement(measurement : BodyMeasurements)
    suspend fun addPayment(payment: Payment)
    suspend fun deleteTraining(event: Event)
    suspend fun deleteMeasurement(measurement : BodyMeasurements)
    suspend fun deletePayment(payment: Payment)
}

class RepositoryImpl (private val dao: TrainingsDAO) : Repository {

    val trainings : Flow<List<Event>> = dao.getAllTrainings()
    val payments : Flow<List<Payment>> = dao.getAllPayments()
    val measurements : Flow<List<BodyMeasurements>> = dao.getAllBodyMeasurements()

    override suspend fun addTraining(event: Event){
        dao.addTraining(event)
    }
    override suspend fun addMeasurement(measurement : BodyMeasurements){
        dao.addBodyMeasurements(measurement)
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

}