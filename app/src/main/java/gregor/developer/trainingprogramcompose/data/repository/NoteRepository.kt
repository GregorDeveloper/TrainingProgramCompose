package gregor.developer.trainingprogramcompose.data.repository

import gregor.developer.trainingprogramcompose.data.entity.NoteItem

interface NoteRepository {

    suspend fun insertItem(item: NoteItem)

    suspend fun deleteItem(item: NoteItem)

    suspend fun getItemByDate(date: String): NoteItem
}