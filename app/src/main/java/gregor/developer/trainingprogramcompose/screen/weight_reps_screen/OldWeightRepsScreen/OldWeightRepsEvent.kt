package gregor.developer.trainingprogramcompose.screen.weight_reps_screen.OldWeightRepsScreen

sealed class OldWeightRepsEvent{

    object previousItemList: OldWeightRepsEvent()
    object nextItemList: OldWeightRepsEvent()
    object openDialogDate: OldWeightRepsEvent()
}
