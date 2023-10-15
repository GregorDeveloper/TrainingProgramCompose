package gregor.developer.trainingprogramcompose.data.static_data

import gregor.developer.trainingprogramcompose.R

class MuscleList {

    val muscleGroupList = listOf<MuscleItem>(
        MuscleItem(R.drawable.fitness_test, R.string.neck),
        MuscleItem(R.drawable.fitness_test, R.string.traps),
        MuscleItem(R.drawable.fitness_test, R.string.shoulders),
        MuscleItem(R.drawable.fitness_test, R.string.chest),
        MuscleItem(R.drawable.fitness_test, R.string.lats),
        MuscleItem(R.drawable.fitness_test, R.string.biceps),
        MuscleItem(R.drawable.fitness_test, R.string.triceps),
        MuscleItem(R.drawable.fitness_test, R.string.forearms),
        MuscleItem(R.drawable.fitness_test, R.string.abdominals),
        MuscleItem(R.drawable.fitness_test, R.string.middle_back),
        MuscleItem(R.drawable.fitness_test, R.string.lower_back),
        MuscleItem(R.drawable.fitness_test, R.string.glutes),
        MuscleItem(R.drawable.fitness_test, R.string.abductors),
        MuscleItem(R.drawable.fitness_test, R.string.quadriceps),
        MuscleItem(R.drawable.fitness_test, R.string.adductors),
        MuscleItem(R.drawable.fitness_test, R.string.hamstrings),
        MuscleItem(R.drawable.fitness_test, R.string.calves),
    )

    val workoutNeck = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_face_up_plate_neck_resistance,
            listOf(R.string.neck, R.string.isolation, R.string.strenght, R.string.intermediate),
            1
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_face_down_plate_neck_resistance,
            listOf(R.string.neck, R.string.isolation, R.string.strenght, R.string.intermediate),
            1
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.isometric_neck_exercise_front_and_back,
            listOf(R.string.neck, R.string.isolation, R.string.strenght, R.string.beginner),
            1
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.side_neck_stretch,
            listOf(R.string.neck, R.string.isolation, R.string.strenght, R.string.beginner),
            1
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.chin_to_chest_stretch,
            listOf(R.string.neck, R.string.n_a, R.string.strenght, R.string.beginner),
            1
        ),
    )

    val workoutTraps = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.standing_dumbbell_upright_row,
            listOf(R.string.traps, R.string.compound, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.upright_row_with_bands,
            listOf(R.string.traps, R.string.compound, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.smith_machine_upright_row,
            listOf(R.string.traps, R.string.compound, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.upright_cable_row,
            listOf(R.string.traps, R.string.compound, R.string.strenght, R.string.intermediate),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.kettlebell_sumo_high_pull,
            listOf(R.string.traps, R.string.compound, R.string.strenght, R.string.intermediate),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.smith_machine_shrug,
            listOf(R.string.traps, R.string.isolation, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cable_shrugs,
            listOf(R.string.traps, R.string.isolation, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_shrugs,
            listOf(R.string.traps, R.string.isolation, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_shrugs,
            listOf(R.string.traps, R.string.isolation, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_shrugs_behind_the_back,
            listOf(R.string.traps, R.string.isolation, R.string.strenght, R.string.beginner),
            2
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.snatch_shrug,
            listOf(
                R.string.traps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            2
        ),
    )

    val workoutShoulders = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_incline_shoulder_raise,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.standing_military_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.machine_shoulder_military_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.shoulder_press_with_bands,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.smith_machine_overhead_shoulder_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bradford_rocky_presses,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.arnold_dumbbell_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_shoulder_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.standing_palms_in_dumbbell_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_one_arm_shoulder_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_kettlebell_push_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.kettlebell_arnold_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.double_kettlebell_push_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.seated_barbell_military_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_shoulder_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.standing_barbell_press_behind_neck,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.iron_cross,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cuban_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.alternating_kettlebell_press,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_kettlebell_clean_jerk,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_kettlebell_jerk,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.double_kettlebell_jerk,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.handstand_push_ups,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.expert),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.upright_barbell_row,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.back_flyes_with_bands,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_raise,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.low_pulley_row_to_neck,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_rear_delt_row,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.two_arm_kettlebell_clean,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_one_arm_upright_row,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.face_pull,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.intermediate),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_kettlebell_snatch,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.expert),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.double_kettlebell_snatch,
            listOf(R.string.shoulders, R.string.compound, R.string.strenght, R.string.expert),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_incline_shoulder_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.standing_low_pulley_deltoid_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_two_dumbbell_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_incline_dumbbell_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_side_laterals,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_incline_lateral_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_cable_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.side_lateral_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.smith_incline_shoulder_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_dumbbell_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.power_partials,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.seated_side_lateral_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_plate_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_dumbbell_straight_arm_front_delt_raise_above_head,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bent_over_low_pulley_side_lateral,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cable_seated_lateral_raise,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.reverse_machine_flyes,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bent_over_dumbbell_rear_delt_raise_with_head_on_bench,
            listOf(R.string.shoulders, R.string.isolation, R.string.strenght, R.string.beginner),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_lying_one_arm_rear_lateral_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_lying_rear_lateral_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.lying_one_arm_lateral_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.reverse_flyes_with_external_rotation,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.lying_rear_delt_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.seated_bent_over_rear_delt_raise,
            listOf(
                R.string.shoulders,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            3
        ),


        )
    val workoutChest = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.machine_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.hummer_grip_incline_db_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.incline_dumbbell_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.extended_range_one_arm_kettlebell_floor_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.smith_machine_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bench_press_with_bands,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_dumbbell_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_dumbbell_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_bench_press_medium_grip,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.decline_barbell_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_incline_bench_press_medium_grip,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cross_over_with_bands,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.leverage_incline_chest_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.pushups,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.pushups_close_and_wide_hand_positions,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.pushups_with_feet_elevated,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.alternating_floor_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.incline_dumbbell_flyes,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.decline_dumbbell_flyes,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.leg_over_floor_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_kettlebell_floor_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_guillotine_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.neck_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.wide_grip_decline_barbell_bench_press,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.around_the_worlds,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dips_chest_version,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.single_arm_push_up,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.push_ups_with_feet_on_an_exercise_ball,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bent_arm_dumbbell_pullover,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.plyo_kettlebell_pushups,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.expert),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.front_raise_and_pullover,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.straight_arm_dumbbell_pullover,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.wide_grip_decline_barbell_pullover,
            listOf(R.string.chest, R.string.compound, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.dumbbell_flyes,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cable_crossover,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.one_arm_flat_bench_dumbbell_flyes,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.flat_bench_cable_flyes,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.incline_cable_flye,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.intermediate),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.butterfly,
            listOf(R.string.chest, R.string.isolation, R.string.strenght, R.string.beginner),
            4
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.svends_press,
            listOf(R.string.chest, R.string.n_a, R.string.strenght, R.string.expert),
            4
        ),
    )

    val workoutLats = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.pullups,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.chin_up,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.close_grip_front_lat_pulldown,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.underhand_cable_pulldown,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.wide_grip_lat_pulldown,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.full_range_of_motion_lat_pulldown,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.side_to_side_chins,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.gironda_sternum_chins,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.rocky_pull_ups_pulldowns,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.wide_grip_rear_pull_up,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.bent_arm_barbell_pullover,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.wide_grip_pulldowns_behind_the_neck,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.v_bar_pulldown,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.elevated_cable_rows,
            listOf(R.string.lats, R.string.compound, R.string.strenght, R.string.intermediate),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.cable_incline_pushdown,
            listOf(R.string.lats, R.string.isolation, R.string.strenght, R.string.beginner),
            5
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.straight_arm_pulldown,
            listOf(R.string.lats, R.string.isolation, R.string.strenght, R.string.beginner),
            5
        ),


        )

    val workoutBiceps = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test), R.string.barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.high_cable_curls,
            listOf(R.string.biceps, R.string.compound, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.drag_curl,
            listOf(R.string.biceps, R.string.compound, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.concentration_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.preacher_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.close_grip_ez_bar_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.incline_hammer_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.two_arm_dumbbell_preacher_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_dumbbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_bicep_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hammer_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_dumbbell_inner_biceps_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_biceps_cable_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_curls_lying_against_an_incline,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.close_grip_standing_barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.wide_grip_standing_barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.incline_dumbbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_alternate_bicep_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.alternate_incline_dumbbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.alternate_hammer_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_dumbbell_preacher_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_close_grip_bar_curl_on_high_pulley,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_cable_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_preacher_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cross_body_hammer_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.ez_bar_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_supine_dumbbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_plate_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.spider_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.preacher_hammer_dumbbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.flexor_incline_dumbbell_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_one_arm_dumbbell_curl_over_incline_bench,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.beginner),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_prone_incline_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_inner_biceps_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_dumbbell_reverse_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_close_grip_concentration_barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_cable_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_barbell_preacher_curls,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.overhead_cable_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_high_bench_barbell_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_one_arm_cable_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.zottman_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.zottman_preacher_curl,
            listOf(R.string.biceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            6
        ),
    )

    val workoutTriceps = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.jm_press,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_machine_close_grip_bench_press,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.close_grip_barbell_bench_press,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dips_triceps_versions,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bench_dips,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_floor_press,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_close_grip_bench_to_skull_crusher,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_triceps_bench_press,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.push_ups_close_triceps_position,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_bench_dip,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_one_arm_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.triceps_dumbbell_kickback,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_dumbbell_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_pronated_dumbbell_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_supinated_dumbbell_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.triceps_pushdown_v_bar_attachment,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.triceps_pushdown_rope_attachment,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.triceps_pushdown,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_grip_triceps_pushdown,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_bent_over_one_arm_dumbbell_triceps_extensions,
            listOf(R.string.triceps, R.string.compound, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.push_ups_close_triceps_position,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_bent_over_one_arm_dumbbell_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_incline_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_lying_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_rope_overhead_triceps_extension,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_dumbbell_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_ez_bar_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_triceps_press,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_overhead_barbell_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.beginner),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.tate_press,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_close_grip_barbell_triceps_press_to_chin,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_one_arm_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_bent_over_two_arm_dumbbell_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_low_pulley_one_arm_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kneeling_cable_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_close_grip_barbell_triceps_extensions_behind_the_head,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_triceps_press,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_dumbbell_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.incline_barbell_triceps_extensions,
            listOf(R.string.triceps, R.string.isolation, R.string.strenght, R.string.intermediate),
            7
        ),
    )

    val workoutForearms = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bottoms_up_clean_from_the_hang_position,
            listOf(R.string.forearms, R.string.compound, R.string.strenght, R.string.intermediate),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_two_arm_palms_up_low_pulley_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.palms_up_dumbbell_wrist_curl_over_a_bench,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.palms_down_dumbbell_wrist_curl_over_a_bench,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.palms_up_barbell_wrist_curl_over_a_bench,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.palms_down_wrist_curl_over_a_bench,
            listOf(R.string.forearms),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_dumbbell_palms_up_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_dumbbell_palms_down_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_palm_up_barbell_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_palms_down_barbell_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_one_arm_dumbbell_palms_up_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_palms_up_barbell_behind_the_back_wrist_curl,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_lying_pronation,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.intermediate),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_lying_supination,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.intermediate),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_olympic_plate_hand_squeeze,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.beginner),
            8
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.plate_pinch,
            listOf(R.string.forearms, R.string.isolation, R.string.strenght, R.string.intermediate),
            8
        ),

        )

    val workoutAbdominals = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.press_sit_up,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.air_bike,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.jackknife_sit_up,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.side_jackknife,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cross_body_crunch,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_oblique_crunch,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.flat_bench_leg_pull_in,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.exercise_ball_pull_in,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_flat_bench_leg_pull_in,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.leg_pull_in,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_reverse_crunch,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_knee_hip_raise,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.butt_ups,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_cable_wood_chop,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_windmill,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_pass_between_the_legs,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.plate_twist,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.gorilla_chin_crunch,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.russian_twist,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_ab_rollout,
            listOf(
                R.string.abdominals,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_press,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hanging_pike,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.advanced_kettlebell_windmill,
            listOf(
                R.string.abdominals,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.oblique_crunches_on_the_floor,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.oblique_crunches,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_side_bend,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_side_bend,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_crunch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_barbell_twist,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.knee_hip_raise_on_parallel_bars,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.flat_bench_lying_leg_raise,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.sit_up,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.janda_sit_up,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.alternate_hell_touchers,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.crunches,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_seated_crunch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_crunch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.exercise_ball_crunch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.crunch_legs_on_exercise_ball,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_crunches,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.tuck_crunch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.crunch_hands_overhead,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_leg_tucks,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_ball_side_bend,
            listOf(
                R.string.abdominals,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_sit_ups_with_bands,
            listOf(
                R.string.abdominals,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.decline_crunch,
            listOf(
                R.string.abdominals,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.frog_sit_ups,
            listOf(
                R.string.abdominals,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hanging_leg_raise,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.plank,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_figure_8,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.double_kettlebell_windmill,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.side_bridge,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.overhead_stretch,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.side_bridge,
            listOf(R.string.abdominals, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.scissor_kick,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.toe_touchers,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_overhead_stretch,
            listOf(R.string.abdominals, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lower_back_curl,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_lateral_stretch,
            listOf(R.string.abdominals, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
    )

    val workoutMiddleBack = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_over_two_dumbbell_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_over_two_dumbbell_row_with_palms_in,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_dumbbell_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_cable_rows,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_over_one_arm_long_bar_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_over_barbell_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_machine_bent_over_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_kettlebell_row,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.two_arm_kettlebell_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_one_arm_cable_pulley_rows,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bent_over_two_arm_long_bar_row,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_grip_bent_over_rows,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_chin_up,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.mixed_grip_chin,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.alternating_renegade_row,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_cambered_barbell_row,
            listOf(R.string.middle_back, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.incline_bench_pull,
            listOf(R.string.middle_back, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.alternating_kettlebell_row,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.middle_back_shrug,
            listOf(
                R.string.middle_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.middle_back_stretch,
            listOf(R.string.middle_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
    )

    val workoutLowerBack = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.stiff_leg_barbell_good_morning,
            listOf(R.string.lower_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_ball_hyper_extension,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_deadlift,
            listOf(R.string.lower_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.stiff_leg_barbell_good_morning,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hyperextension_back_extensions,
            listOf(R.string.lower_back, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.superman,
            listOf(R.string.lower_back, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.pelvic_tilt_into_bridge,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_pelvic_tilt,
            listOf(R.string.lower_back, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hug_a_bull,
            listOf(R.string.lower_back, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.crossover_reverse_lunge,
            listOf(R.string.lower_back, R.string.n_a, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cat_stretch,
            listOf(R.string.lower_back, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hug_knees_to_chest,
            listOf(R.string.lower_back, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dancers_stretch,
            listOf(R.string.lower_back, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.pyramid,
            listOf(R.string.lower_back, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.deficit_deadlift,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.rack_pulls,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.rack_pulls_with_bands,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_good_mornings,
            listOf(
                R.string.lower_back,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_band_deadlift,
            listOf(R.string.lower_back, R.string.compound, R.string.powerlifting, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.deadlift_with_bands,
            listOf(R.string.lower_back, R.string.compound, R.string.powerlifting, R.string.expert),
            9
        ),
    )

    val workoutGlutes = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.glutes_kickback,
            listOf(R.string.glutes, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.flutter_kicks,
            listOf(R.string.glutes, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.leg_lift,
            listOf(R.string.glutes, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.butt_lift_bridge,
            listOf(R.string.glutes, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_legged_cable_kickback,
            listOf(R.string.glutes, R.string.isolation, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.downward_facing_balance,
            listOf(R.string.glutes, R.string.isolation, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_knee_to_chest,
            listOf(R.string.glutes, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.knee_across_the_body,
            listOf(R.string.glutes, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.ankle_on_the_knee,
            listOf(R.string.glutes, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_glute_bridge,
            listOf(
                R.string.glutes,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_hip_thrust,
            listOf(
                R.string.glutes,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kneeling_squat,
            listOf(
                R.string.glutes,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
    )

    val workoutAbductors = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.thigh_abductors,
            listOf(R.string.abductors, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.side_leg_raises,
            listOf(R.string.abductors, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
    )

    val workoutQuadriceps = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_side_split_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_lunges,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.leg_press,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_machine_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_single_leg_split_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.plie_dumbbell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.goblet_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.bodyweight_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.squats_with_bands,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_rear_lunge,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_lunge,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.front_squats_with_two_kettlebells,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_full_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.front_squats_clean_grip,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.narrow_stance_leg_press,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_step_ups,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_step_ups,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.jefferson_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_squat_to_a_bench,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_hack_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.narrow_stance_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.wide_stance_barbell_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.freehand_jump_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.speed_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.zercher_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_pistol_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_overhead_kettlebell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.front_barbell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.front_barbell_squat_to_a_bench,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_leg_barbell_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_squat_to_a_bench,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.weighted_sissy_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.squat_jerk,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_side_deadlift,
            listOf(R.string.quadriceps, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.leg_extensions,
            listOf(R.string.quadriceps, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.cable_hip_adduction,
            listOf(R.string.quadriceps, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.box_squat_with_bands,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_band_box_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.squat_with_bands,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.box_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.reverse_band_power_squat,
            listOf(R.string.quadriceps, R.string.compound, R.string.powerlifting, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.box_squat_with_bands,
            listOf(R.string.quadriceps, R.string.compound, R.string.powerlifting, R.string.expert),
            9
        ),


        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.olympic_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.frankenstein_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.heaving_snatch_balance,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.split_jerk,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.jerk_dip_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.snatch_balance,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.overhead_squat,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.power_jerk,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.split_clean,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hang_clean,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hang_clean_bellow_the_knees,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.clean_from_blocks,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.clean_pull,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.snatch,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.power_snatch_from_blocks,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.snatch_from_blocks,
            listOf(
                R.string.quadriceps,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),
    )

    val workoutAdductors = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.thigh_adductors,
            listOf(R.string.adductors, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),

        )

    val workoutHamstrings = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_machine_stiff_legged_deadlift,
            listOf(R.string.hamstrings, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.stiff_legged_dumbbell_deadlift,
            listOf(R.string.hamstrings, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_kettlebell_clean,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.one_arm_kettlebell_swings,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_dead_clean,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.romanian_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.snatch_pull,
            listOf(R.string.hamstrings, R.string.compound, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.kettlebell_one_legged_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.stiff_legged_barbell_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.double_kettlebell_alternating_hang_clean,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.open_palm_kettlebell_clean,
            listOf(R.string.hamstrings, R.string.compound, R.string.strenght, R.string.expert),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.lying_leg_curls,
            listOf(R.string.hamstrings, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_leg_curl,
            listOf(R.string.hamstrings, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.ball_leg_curl,
            listOf(R.string.hamstrings, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hamstring_stretch,
            listOf(R.string.hamstrings, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.chair_leg_extended_stretch,
            listOf(R.string.hamstrings, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.intermediate_groin_stretch,
            listOf(
                R.string.hamstrings,
                R.string.isolation,
                R.string.strenght,
                R.string.intermediate
            ),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.split_squats,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.front_leg_raises,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.the_straddle,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_toe_touches,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.seated_hamstring_and_calf_stretch,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.intermediate),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.worlds_greatest_stretch,
            listOf(R.string.hamstrings, R.string.n_a, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.good_morning,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.good_morning_off_pins,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.glute_ham_raise,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.sumo_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.powerlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.clean_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.beginner
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.clean,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.romanian_deadlift_from_deficit,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.snatch_deadlift,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.muscle_snatch,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.power_clean_from_blocks,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.intermediate
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.power_snatch,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),


        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hang_snatch,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.hang_snatch_below_knees,
            listOf(
                R.string.hamstrings,
                R.string.compound,
                R.string.olympic_weightlifting,
                R.string.expert
            ),
            9
        ),
    )

    val workoutCalves = listOf<WorkoutItem>(
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.dumbbell_seated_one_leg_calf_raise,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),

        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.calf_press_on_the_leg_press_machine,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_calf_raises,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_barbell_calf_raise,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.barbell_seated_calf_raise,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.smith_machine_reverse_calf_raises,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.rocking_standing_calf_raise,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.beginner),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.calf_raise_on_a_dumbbell,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.intermediate),
            9
        ),
        WorkoutItem(
            listOf(R.drawable.fitness_test),
            R.string.standing_dumbbell_calf_raise,
            listOf(R.string.calves, R.string.isolation, R.string.strenght, R.string.intermediate),
            9
        ),
    )
}