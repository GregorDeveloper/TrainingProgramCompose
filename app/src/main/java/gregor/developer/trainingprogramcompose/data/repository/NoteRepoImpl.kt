package gregor.developer.trainingprogramcompose.data.repository

import gregor.developer.trainingprogramcompose.data.dao.NoteDao
import gregor.developer.trainingprogramcompose.data.entity.NoteItem

class NoteRepoImpl(
    private val dao: NoteDao
): NoteRepository {
    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override suspend fun getItemByDate(date: String): NoteItem {
        return dao.getItemByDate(date)
    }

}