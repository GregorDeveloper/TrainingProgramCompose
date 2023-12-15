package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.NoteScreen

sealed class NoteEvent {

    data class OnTextChange(val text: String): NoteEvent()

    data class OnNoteSave(val note: String): NoteEvent()
}