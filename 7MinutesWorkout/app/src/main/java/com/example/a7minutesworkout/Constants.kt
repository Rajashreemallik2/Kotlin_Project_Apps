package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val lunge =ExerciseModel(
            1,
            "lunge",
             R.drawable.lunge,
            false,
            false
        )
         exerciseList.add(lunge)

        val planks =ExerciseModel(
            2,
            "planks",
            R.drawable.planks,
            false,
            false
        )
        exerciseList.add(planks)

        val pushUp =ExerciseModel(
            3,
            "pushUp",
            R.drawable.push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val pushUpAndRotation =ExerciseModel(
            4,
            "pushUpAndRotation",
            R.drawable.push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(pushUpAndRotation)

        val sidePlank =ExerciseModel(
            5,
            "sidePlank",
            R.drawable.side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)

        val squats =ExerciseModel(
            6,
            "squats",
            R.drawable.squats,
            false,
            false
        )
        exerciseList.add(squats)

        val stepUpOntoChair =ExerciseModel(
            7,
            "stepUpOntoChair",
            R.drawable.step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUpOntoChair)

        val triCepDipsOnChair =ExerciseModel(
            8,
            "triCepDipsOnChair",
            R.drawable.tricep_dips_on_chair,
            false,
            false
        )
        exerciseList.add(triCepDipsOnChair)
        return exerciseList
    }
}